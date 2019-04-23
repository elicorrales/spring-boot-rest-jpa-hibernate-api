'use strict';
const customerOrdersListAreaElem = document.getElementById('customerOrdersListArea');

var customerOrdersList = {};


const displayCustomerOrder = (idx, customer) => {
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

const displayCustomerOrdersList = (customers) => {
    let html = '';
    customers.forEach( (customer,idx) => {
       html += displayCustomer(idx,customer); 
    });
    return html;
}

const getCustomerOrdersList = () => {
    axios.get('/customers/customer/'+idOfCustomerSelectedFromList+'/orders')
    .then(
        result => {
            //app.alerts.displayMessage('success','Got Customers..');
            customerListAreaElem. innerHTML = displayCustomerList(result.data);
        }
    )
    .catch(
        error => {
            let msg = error.response;
            msg = error.response.data;
            msg = error.response.data.message;
            let message = error.response && error.response.data && error.response.data.message ? error.response.data.message:error;
            app.alerts.displayMessage('danger',message);
        }
    )
}

customerOrdersList.getCustomerOrdersList = getCustomerOrdersList;
app.customerOrdersList = customerOrdersList;