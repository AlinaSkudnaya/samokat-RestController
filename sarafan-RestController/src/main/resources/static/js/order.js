function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var orderApi = Vue.resource('/Order{/id}');

Vue.component('order-form', {
    props: ['orders', 'orderAttr'],
    data: function() {
        return {
            status: '',
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
        orderAttr: function(newVal, oldVal) {
            this.status=newVal.status;
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
            var order = { name: this.name, surname: this.surname,
                patronymic: this.patronymic, phone: this.phone , samokaty: this.samokaty,
                coun: this.coun , status: this.status};

            if (this.id) {
                orderApi.update({id: this.id}, order).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.orders, data.id,
                            this.name, this.surname, this.patronymic,
                            this.phone, this.samokaty, this.coun, this.status);

                        this.orders.splice(index, 1, data);
                        this.status=''
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
                orderApi.save({}, order).then(result =>
                    result.json().then(data => {
                        this.orders.push(data);
                        this.status=''
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

Vue.component('order-row', {
    props: ['order', 'editMethod', 'orders'],
    template:
        '<div> ' +
        '<i>({{ order.id }})</i>  ' +
        ' {{ order.name }}'+
        '  {{ order.surname }}'+
        '  {{ order.patronymic }}'+
        '  {{ order.phone }}'+
        '  {{ order.samokaty }}'+
        '  {{ order.coun }}'+
        '  {{ order.status }}'+

        '<span style=" right: 20;position: absolute ">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.order);
        },
        del: function() {
            orderApi.remove({id: this.order.id}).then(result => {
                if (result.ok) {
                    this.orders.splice(this.orders.indexOf(this.order), 1)
                }
            })
        }
    }
});

Vue.component('orders-list', {
    props: ['orders'],
    data: function() {
        return {
            order: null
        }
    },
    template:
        '<div style="position: relative; width: 550px;">' +
        '<order-form :orders="orders" :orderAttr="order" />' +
        '<order-row v-for="order in orders" :key="order.id" :order="order" ' +
        ':editMethod="editMethod" :orders="orders" />' +
        '</div>',
    created: function() {
        orderApi.get().then(result =>
            result.json().then(data =>
                data.forEach(order => this.orders.push(order))
            )
        )
    },
    methods: {
        editMethod: function(order) {
            this.order = order;

        }
    }
});

var app = new Vue({
    el: '#app1',
    template: '<orders-list :orders="orders" />',
    data: {
        orders: []
    }
});