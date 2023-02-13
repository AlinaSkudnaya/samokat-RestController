function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var zayavkaApi = Vue.resource('/Zayavka{/id}');

Vue.component('zayavka-form', {
    props: ['zayavkas', 'zayavkaAttr'],
    data: function() {
        return {


            coun: '',

            name: '',
            id: ''
        }
    },
    watch: {
        zayavkaAttr: function(newVal, oldVal) {


            this.coun=newVal.coun;

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
        '<input type="text" placeholder="Write count" v-model="coun" />' +
        '</div>' +


        '<div>' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>' +
        '</form>'+
        '</div>',
    methods: {
        save: function() {
            var zayavka = { name: this.name,

                coun: this.coun };

            if (this.id) {
                zayavkaApi.update({id: this.id}, zayavka).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.zayavkas, data.id,
                            this.name,  this.coun);

                        this.zayavkas.splice(index, 1, data);

                        this.coun=''

                        this.name =''
                        this.id =''
                    })
                )


            } else {
                zayavkaApi.save({}, zayavka).then(result =>
                    result.json().then(data => {
                        this.zayavkas.push(data);


                        this.coun=''

                        this.name =''
                        this.id =''
                    })
                )
            }
        }
    }
});

Vue.component('zayavka-row', {
    props: ['zayavka', 'editMethod', 'zayavkas'],
    template:
        '<div> ' +
        '<i>({{ zayavka.id }})</i>  ' +
        ' {{ zayavka.name }}'+

        '  {{ zayavka.coun }}'+


        '<span style=" right: 20;position: absolute ">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.zayavka);
        },
        del: function() {
            zayavkaApi.remove({id: this.zayavka.id}).then(result => {
                if (result.ok) {
                    this.zayavkas.splice(this.zayavkas.indexOf(this.zayavka), 1)
                }
            })
        }
    }
});

Vue.component('zayavkas-list', {
    props: ['zayavkas'],
    data: function() {
        return {
            zayavka: null
        }
    },
    template:
        '<div style="position: relative; width: 1000px;">' +
        '<zayavka-form :zayavkas="zayavkas" :zayavkaAttr="zayavka" />' +
        '<zayavka-row v-for="zayavka in zayavkas" :key="zayavka.id" :zayavka="zayavka" ' +
        ':editMethod="editMethod" :zayavkas="zayavkas" />' +
        '</div>',
    created: function() {
        zayavkaApi.get().then(result =>
            result.json().then(data =>
                data.forEach(zayavka => this.zayavkas.push(zayavka))
            )
        )
    },
    methods: {
        editMethod: function(zayavka) {
            this.zayavka = zayavka;

        }
    }
});

var app = new Vue({
    el: '#app3',
    template: '<zayavkas-list :zayavkas="zayavkas" />',
    data: {
        zayavkas: []
    }
});