function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var arendaApi = Vue.resource('/Arenda{/id}');

Vue.component('arenda-form', {
    props: ['arendas', 'arendaAttr'],
    data: function() {
        return {

            status: '',

            day_end: '',
            day_start: '',
            coun: '',
            samokaty: '',
            phone: '',
            patronymic: '',
            surname: '',
            name: '',
            id: ''
        }
    },
    watch: {
        arendaAttr: function(newVal, oldVal) {

            this.status=newVal.status;
            this.day_end=newVal.day_end;
            this.day_start=newVal.day_start;

            this.coun=newVal.coun;
            this.samokaty=newVal.samokaty;
            this.phone=newVal.phone;
            this.patronymic=newVal.patronymic;
            this.surname = newVal.surname;
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<form>'+

        '<div>' +
        '<input type="text" placeholder="Write name" v-model="name" />' +
        '</div>' +

        '<div>' +
        '<input type="text" placeholder="Write surname" v-model="surname" /><br>' +
        '</div>' +

        '<div>' +
        '<input type="text" placeholder="Write patronymic" v-model="patronymic" /><br>' +
        '</div>' +

        '<div>' +
        '<input type="text" placeholder="Write phone" v-model="phone" />' +
        '</div>' +

        '<div>' +
        '<input type="text" placeholder="Write samokat" v-model="samokaty" />' +
        '</div>' +


        '<div>' +
        '<input type="text" placeholder="Write count" v-model="coun" />' +
        '</div>' +

        '<div>' +
        '<input type="text" placeholder="Write first day" v-model="day_start" />' +
        '</div>' +


        '<div>' +
        '<input type="text" placeholder="Write second day" v-model="day_end" />' +
        '</div>' +

        '<div>' +
        '<label >Choose a status:</label>'+
        '<select  v-model="status">\n' +
        '    <option value="Обработан">Обработан</option>\n' +
        '    <option value="Не обработан">Необработан</option>\n' +
        '    <option value="Отказ">Отказ</option>\n' +
        '    <option value="Недозвон">Недозвон</option>\n' +
        '</select>' +
        '</div>' +


        '<div>' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>' +
        '</form>'+
        '</div>',
    methods: {
        save: function() {
            var arenda = { name: this.name, surname: this.surname,
                patronymic: this.patronymic, phone: this.phone , samokaty: this.samokaty,
                coun: this.coun , day_start: this.day_start, day_end: this.day_end, status:this.status};

            if (this.id) {
                arendaApi.update({id: this.id}, arenda).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.arendas, data.id,
                            this.name, this.surname, this.patronymic,
                            this.phone, this.samokaty, this.coun,  this.day_start, this.day_end, this.status,);

                        this.arendas.splice(index, 1, data);
                        this.status=''
                        this.day_end=''
                        this.day_start=''
                        this.coun=''
                        this.samokaty=''
                        this.phone=''
                        this.patronymic=''
                        this.surname =''
                        this.name =''
                        this.id =''
                    })
                )


            } else {
                arendaApi.save({}, arenda).then(result =>
                    result.json().then(data => {
                        this.arendas.push(data);

                        this.status=''
                        this.day_end=''
                        this.day_start=''

                        this.coun=''
                        this.samokaty=''
                        this.phone=''
                        this.patronymic=''
                        this.surname =''
                        this.name =''
                        this.id =''
                    })
                )
            }
        }
    }
});

Vue.component('arenda-row', {
    props: ['arenda', 'editMethod', 'arendas'],
    template:
        '<div> ' +
        '<i>({{ arenda.id }})</i>  ' +
        ' {{ arenda.name }}'+
        '  {{ arenda.surname }}'+
        '  {{ arenda.patronymic }}'+
        '  {{ arenda.phone }}'+
        '  {{ arenda.samokaty }}'+
        '  {{ arenda.coun }}'+
        '  {{ arenda.day_start }}'+
        '  {{ arenda.Day_end }}'+
        '  {{ arenda.status }}'+

        '<span style=" right: 20;position: absolute ">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.arenda);
        },
        del: function() {
            arendaApi.remove({id: this.arenda.id}).then(result => {
                if (result.ok) {
                    this.arendas.splice(this.arendas.indexOf(this.arenda), 1)
                }
            })
        }
    }
});

Vue.component('arendas-list', {
    props: ['arendas'],
    data: function() {
        return {
            arenda: null
        }
    },
    template:
        '<div style="position: relative; width: 1000px;">' +
        '<arenda-form :arendas="arendas" :arendaAttr="arenda" />' +
        '<arenda-row v-for="arenda in arendas" :key="arenda.id" :arenda="arenda" ' +
        ':editMethod="editMethod" :arendas="arendas" />' +
        '</div>',
    created: function() {
        arendaApi.get().then(result =>
            result.json().then(data =>
                data.forEach(arenda => this.arendas.push(arenda))
            )
        )
    },
    methods: {
        editMethod: function(arenda) {
            this.arenda = arenda;

        }
    }
});

var app = new Vue({
    el: '#app2',
    template: '<arendas-list :arendas="arendas" />',
    data: {
        arendas: []
    }
});