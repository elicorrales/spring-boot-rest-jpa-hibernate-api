'use strict';
const customerListAreaElem = document.getElementById('customerListArea');

var customerList = {};


const displayCustomer = (idx, customer) => {
    let html = ''
        + '<tr>'
        + '    <td id="custId-' + customer.id + '">' + customer.id + '</td>'
        + '    <td id="custFname-' + customer.id + '">' + customer.fname + '</td>'
        + '    <td id="custLname-' + customer.id + '">' + customer.lname + '</td>'
        + '    <td id="custEmail-' + customer.id + '">' + customer.email + '</td>'
        //+ '    <td><a href="" onclick="onEditCustomerClickDoEditCustomer(event,'+customer.id+')"> <i class="glyphicon glyphicon-pencil"></i> </span></a></td>'
        //+ '    <td><a href="" onclick="onDeleteCustomerClickDoDeleteCustomer(event,'+customer.id+')"> <i class="glyphicon glyphicon-trash"></i> </span></a></td>'
        //+ '    <td><a href="" onclick="onGoToCustomerOrdersClickDoGoToCustomerOrders(event,'+customer.id+')"> <i class="glyphicon glyphicon-usd"></i> </span></a></td>'
        + '    <td><h5><a href="" onclick="onEditCustomerClickDoEditCustomer(event,'+customer.id+')"> <i class="glyphicon glyphicon-pencil"></i> </a> '
        + '    <a href="" onclick="onDeleteCustomerClickDoDeleteCustomer(event,'+customer.id+')"> <i class="glyphicon glyphicon-trash"></i> </a> '
        + '    <a href="" onclick="onGoToCustomerOrdersClickDoGoToCustomerOrders(event,'+customer.id+')"> <i class="glyphicon glyphicon-usd"></i> </a></h5></td>'
        + '</tr>';

    return html;
}

const displayCustomerList = (customers) => {
    if (customers===undefined || customers.length<1) {
        app.alerts.displayMessage('warning','No Customers');
        return '';
    }
    let html = '';
    customers.forEach( (customer,idx) => {
       html += displayCustomer(idx,customer); 
    });
    return html;
}

const getCustomerList = () => {
    axios.get('/customers')
    .then(
        result => {
            //app.alerts.displayMessage('success','Got Customers..');
            customerListAreaElem. innerHTML = displayCustomerList(result.data);
        }
    )
    .catch(
        error => {
            app.utils.displayError(error);
        }
    )
}

customerList.getCustomerList = getCustomerList;
app.customerList = customerList;