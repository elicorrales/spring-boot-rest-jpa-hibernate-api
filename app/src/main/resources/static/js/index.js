'use strict';

var app = {};

const customerListSectionElem = document.getElementById('customerListSection');
const newCustomerSectionElem = document.getElementById('newCustomerSection');

const showCustomerListSection = () => {
    customerListSectionElem.style.display = 'block';
    newCustomerSectionElem.style.display = 'none';
    app.customerList.getCustomerList();
}

const hideCustomerListSection = () => {
    customerListSectionElem.style.display = 'none';
    newCustomerSectionElem.style.display = 'block';
}

const onAddCustomerBtnClickDoAddCustomer = () => {
    app.alerts.hideMessage();
    hideCustomerListSection();
}

app.showCustomerListSection = showCustomerListSection;
app.hideCustomerListSection = hideCustomerListSection;

window.onload = () => {
    app.customerList.getCustomerList();
}