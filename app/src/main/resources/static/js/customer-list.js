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
        + '    <td><a href="" onclick="onEditCustomerClickDoEditCustomer(event,'+customer.id+')"><span class="glyphicon glyphicon-edit"></span></a></td>'
        + '    <td><a href="" onclick="onDeleteCustomerClickDoDeleteCustomer(event,'+customer.id+')"><span class="glyphicon glyphicon-trash"></span></a></td>'
        + '</tr>';

    return html;
}

const displayCustomerList = (customers) => {
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
            let msg = error.response;
            msg = error.response.data;
            msg = error.response.data.message;
            let message = error.response && error.response.data && error.response.data.message ? error.response.data.message:error;
            app.alerts.displayMessage('danger',message);
        }
    )
}

customerList.getCustomerList = getCustomerList;
app.customerList = customerList;