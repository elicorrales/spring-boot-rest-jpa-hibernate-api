'use strict';

var app = {};

const customerOverallSectionElem = document.getElementById('customerOverallSection');
const customerListSectionElem = document.getElementById('customerListSection');
const newCustomerSectionElem = document.getElementById('newCustomerSection');
const editCustomerSectionElem = document.getElementById('editCustomerSection');
const deleteCustomerSectionElem = document.getElementById('deleteCustomerSection');

const customerOverallOrderSectionElem = document.getElementById('customerOverallOrderSection');
const customerOrderListHeadingElem = document.getElementById('customerOrderListHeading');

const showCustomerListSection = () => {
    customerListSectionElem.style.display = 'block';
    newCustomerSectionElem.style.display = 'none';
    editCustomerSectionElem.style.display = 'none';
    deleteCustomerSectionElem.style.display = 'none';
    customerOverallOrderSectionElem.style.display = 'none';
    app.customerList.getCustomerList();
}

const hideCustomerListSection = (why) => {
    customerListSectionElem.style.display = 'none';
    if (why === 'forNew') {
        newCustomerSectionElem.style.display = 'block';
    } else if (why === 'forEdit') {
        editCustomerSectionElem.style.display = 'block';
    } else if (why === 'forDelete') {
        deleteCustomerSectionElem.style.display = 'block';
    }
}

const onAddCustomerBtnClickDoAddCustomer = () => {
    app.alerts.hideMessage();
    hideCustomerListSection('forNew');
}

const onEditCustomerClickDoEditCustomer = (event,id) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let custId = document.getElementById('custId-'+id).value;
    let custFname = document.getElementById('custFname-'+id).value;
    let custLname = document.getElementById('custLname-'+id).value;
    let custEmail = document.getElementById('custEmail-'+id).value;
    //app.alerts.displayMessage('info', custId + ','+custFname+','+custLname);
    app.hideCustomerListSection('forEdit');
    let customer = {};
    customer.id = custId;
    customer.fname = custFname;
    customer.lname = custLname;
    customer.email = custEmail;
    app.customerEdit.editCustomer(customer);
}

const onDeleteCustomerClickDoDeleteCustomer = (event,id) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let custId = document.getElementById('custId-'+id).value;
    let custFname = document.getElementById('custFname-'+id).value;
    let custLname = document.getElementById('custLname-'+id).value;
    let custEmail = document.getElementById('custEmail-'+id).value;
    //app.alerts.displayMessage('info', custId + ','+custFname+','+custLname);
    app.hideCustomerListSection('forDelete');
    let customer = {};
    customer.id = custId;
    customer.fname = custFname;
    customer.lname = custLname;
    customer.email = custEmail;
    app.customerDelete.deleteCustomer(customer);
}

const onCancelBtnClickDoCancel = (event,what) => {
    event.preventDefault();
    app.alerts.hideMessage();
    app.alerts.displayMessage('warning', '"' + what + '" Canceled');
    app.showCustomerListSection();
}

const onCustomerOrdersClickDoCustomerOrders = (event,id) => {
    event.preventDefault();
    customerOverallSectionElem.style.display = 'none';
    customerOverallOrderSectionElem.style.display = 'block';
    let custFname = document.getElementById('custFname-'+id).value;
    let custLname = document.getElementById('custLname-'+id).value;
    customerOrderListHeadingElem.innerHTML = custFname + ' ' + custLname;
}

app.showCustomerListSection = showCustomerListSection;
app.hideCustomerListSection = hideCustomerListSection;

window.onload = () => {
    app.customerList.getCustomerList();
}