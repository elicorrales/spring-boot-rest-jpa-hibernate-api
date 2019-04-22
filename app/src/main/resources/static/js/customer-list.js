'use strict';
const customerListAreaElem = document.getElementById('customerListArea');

var customerList = {};

const displayCustomer = (idx, customer) => {
    let html = ''
        + '<tr>'
        + '    <td>' + idx + '</td>'
        + '    <td>' + customer.fname + '</td>'
        + '    <td>' + customer.lname + '</td>'
        + '    <td>' + customer.email + '</td>'
        + '    <td><span class="glyphicon glyphicon-edit"></span></td>'
        + '    <td><span class="glyphicon glyphicon-trash"></span></td>'
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