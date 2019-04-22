'use strict';

var app = {};

const customerListSectionElem = document.getElementById('customerListSection');
const newCustomerSectionElem = document.getElementById('newCustomerSection');
const editCustomerSectionElem = document.getElementById('editCustomerSection');
const deleteCustomerSectionElem = document.getElementById('deleteCustomerSection');

const showCustomerListSection = () => {
    customerListSectionElem.style.display = 'block';
    newCustomerSectionElem.style.display = 'none';
    editCustomerSectionElem.style.display = 'none';
    deleteCustomerSectionElem.style.display = 'none';
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
    let custId = document.getElementById('custId-'+id).innerText;
    let custFname = document.getElementById('custFname-'+id).innerText;
    let custLname = document.getElementById('custLname-'+id).innerText;
    let custEmail = document.getElementById('custEmail-'+id).innerText;
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
    let custId = document.getElementById('custId-'+id).innerText;
    let custFname = document.getElementById('custFname-'+id).innerText;
    let custLname = document.getElementById('custLname-'+id).innerText;
    let custEmail = document.getElementById('custEmail-'+id).innerText;
    //app.alerts.displayMessage('info', custId + ','+custFname+','+custLname);
    app.hideCustomerListSection('forDelete');
    let customer = {};
    customer.id = custId;
    customer.fname = custFname;
    customer.lname = custLname;
    customer.email = custEmail;
    app.customerDelete.deleteCustomer(customer);
}

const onCancelDeleteCustomerBtnClickDoCancelDeleteCustomer = (event) => {
    event.preventDefault();
    app.alerts.hideMessage();
    app.alerts.displayMessage('warning', 'Delete Canceled');
    app.showCustomerListSection();
}

app.showCustomerListSection = showCustomerListSection;
app.hideCustomerListSection = hideCustomerListSection;

window.onload = () => {
    app.customerList.getCustomerList();
}