function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var samokatApi = Vue.resource('/samokat{/id}');

Vue.component('samokat-form', {
    props: ['samokats', 'samokatAttr'],
    data: function() {
        return {
            price: '',
            color: '',
            seat: '',
            speed: '',
            name_samokat: '',
            id: ''
        }
    },
    watch: {
        samokatAttr: function(newVal, oldVal) {
            this.price=newVal.price;
            this.color=newVal.color;
            this.seat=newVal.seat;
            this.speed = newVal.speed;
            this.name_samokat = newVal.name_samokat;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<form>'+

        '<div>' +
        '<input type="text" placeholder="Write name" v-model="name_samokat" />' +
        '</div>' +

        '<div>' +
        '<input type="text" placeholder="Write speed" v-model="speed" /><br>' +
        '</div>' +

        '<div>' +
        '<label >Choose a seat:</label>'+
        '<select  v-model="seat">\n' +
        '    <option value="Yes">Yes</option>\n' +
        '    <option value="No">No</option>\n' +
        '</select>' +
        '</div>' +

        '<div>' +
        '<input type="text" placeholder="Write color" v-model="color" />' +
        '</div>' +

        '<div>' +
        '<input type="text" placeholder="Write price" v-model="price" />' +
        '</div>' +

        '<div>' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>' +
        '</form>'+
        '</div>',
    methods: {
        save: function() {
            var samokat = { name_samokat: this.name_samokat, speed: this.speed,
                color: this.color, seat: this.seat , price: this.price };

            if (this.id) {
                samokatApi.update({id: this.id}, samokat).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.samokats, data.id, this.speed);
                        this.samokats.splice(index, 1, data);
                        this.price = ''
                        this.color = ''
                        this.seat = ''
                        this.speed = ''
                        this.name_samokat = ''
                        this.id = ''
                    })
                )


            } else {
                samokatApi.save({}, samokat).then(result =>
                    result.json().then(data => {
                        this.samokats.push(data);
                        this.name_samokat = ''
                        this.speed = ''
                        this.price = ''
                        this.color = ''
                        this.seat = ''
                    })
                )
            }
        }
    }
});

Vue.component('samokat-row', {
    props: ['samokat', 'editMethod', 'samokats'],
    template:
        '<div> ' +
        '<i>({{ samokat.id }})</i>   {{ samokat.name_samokat }}'+

        '  {{ samokat.speed }}'+'  '+'  {{ samokat.seat }}'+'  '+'  {{ samokat.color }}'+'  '+'  {{ samokat.price }}'+
        '<span style=" right: 20;position: absolute ">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.samokat);
        },
        del: function() {
            samokatApi.remove({id: this.samokat.id}).then(result => {
                if (result.ok) {
                    this.samokats.splice(this.samokats.indexOf(this.samokat), 1)
                }
            })
        }
    }
});

Vue.component('samokats-list', {
    props: ['samokats'],
    data: function() {
        return {
            samokat: null
        }
    },
    template:
        '<div style="position: relative; width: 500px;">' +
        '<samokat-form :samokats="samokats" :samokatAttr="samokat" />' +
        '<samokat-row v-for="samokat in samokats" :key="samokat.id" :samokat="samokat" ' +
        ':editMethod="editMethod" :samokats="samokats" />' +
        '</div>',
    created: function() {
        samokatApi.get().then(result =>
            result.json().then(data =>
                data.forEach(samokat => this.samokats.push(samokat))
            )
        )
    },
    methods: {
        editMethod: function(samokat) {
            this.samokat = samokat;

        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<samokats-list :samokats="samokats" />',
    data: {
        samokats: []
    }
});