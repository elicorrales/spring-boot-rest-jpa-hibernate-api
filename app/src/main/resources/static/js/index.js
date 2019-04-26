'use strict';


const customerOverallSectionElem = document.getElementById('customerOverallSection');
const customerListSectionElem = document.getElementById('customerListSection');
const newCustomerSectionElem = document.getElementById('newCustomerSection');
const editCustomerSectionElem = document.getElementById('editCustomerSection');
const deleteCustomerSectionElem = document.getElementById('deleteCustomerSection');

const customerOverallOrdersSectionElem = document.getElementById('customerOverallOrdersSection');
const customerOrdersListHeadingElem = document.getElementById('customerOrdersListHeading');
const customerOrdersAddHeadingElem = document.getElementById('customerOrdersAddHeading');
const customerOrdersListSectionElem = document.getElementById('customerOrdersListSection');
const newCustomerOrdersSectionElem = document.getElementById('newCustomerOrdersSection');
const editCustomerOrdersSectionElem = document.getElementById('editCustomerOrdersSection');
const deleteCustomerOrdersSectionElem = document.getElementById('deleteCustomerOrdersSection');


/********************************************************************************************
 * Begin Customer Sections Stuff
 *******************************************************************************************/

const showCustomerListSection = () => {
    customerListSectionElem.style.display = 'block';
    newCustomerSectionElem.style.display = 'none';
    editCustomerSectionElem.style.display = 'none';
    deleteCustomerSectionElem.style.display = 'none';
    customerOverallOrdersSectionElem.style.display = 'none';
    customerList.getCustomerList();
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

const onAddCustomerBtnClickDoShowAddCustomer = () => {
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
    hideCustomerListSection('forEdit');
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

const onCancelCustomerOperationBtnClickDoCancelCustomerOperation = (event,what) => {
    event.preventDefault();
    app.alerts.hideMessage();
    if (what !== 'Back') {
        app.alerts.displayMessage('warning', '"' + what + '" Canceled');
    }
    customerOverallOrdersSectionElem.style.display = 'none';
    customerOverallSectionElem.style.display = 'block';
    showCustomerListSection();
}

const onGoToCustomerOrdersClickDoGoToCustomerOrders = (event,id) => {
    event.preventDefault();
    app.alerts.hideMessage();
    customerOverallSectionElem.style.display = 'none';
    customerOverallOrdersSectionElem.style.display = 'block';
    //let custFnameElem = document.getElementById('custFname-'+id);
    //let custFname = custFnameElem.innerText;
    let custFname = document.getElementById('custFname-'+id).innerText;
    let custLname = document.getElementById('custLname-'+id).innerText;
    customerOrdersListHeadingElem.innerHTML = custFname + ' ' + custLname;
    idOfCustomerSelectedFromList = id;
    app.customerOrdersList.getCustomerOrdersList();
}


/********************************************************************************************
 * Begin Customer Orders Sections Stuff
 *******************************************************************************************/

const showCustomerOrdersListSection = () => {
    customerOrdersListSectionElem.style.display = 'block';
    newCustomerOrdersSectionElem.style.display = 'none';
    editCustomerOrdersSectionElem.style.display = 'none';
    deleteCustomerOrdersSectionElem.style.display = 'none';
    customerOverallOrdersSectionElem.style.display = 'block';
    app.customerOrdersList.getCustomerOrdersList();
}

const hideCustomerOrdersListSection = (why) => {
    customerOrdersListSectionElem.style.display = 'none';
    if (why === 'forNew') {
        newCustomerOrdersSectionElem.style.display = 'block';
    } else if (why === 'forEdit') {
        editCustomerOrdersSectionElem.style.display = 'block';
    } else if (why === 'forDelete') {
        deleteCustomerOrdersSectionElem.style.display = 'block';
    }
}

const onAddCustomerOrderBtnClickDoShowAddCustomerOrder = () => {
    app.alerts.hideMessage();
    hideCustomerOrdersListSection('forNew');
    let custFname = document.getElementById('custFname-'+ idOfCustomerSelectedFromList).innerText;
    let custLname = document.getElementById('custLname-'+ idOfCustomerSelectedFromList).innerText;
    customerOrdersAddHeadingElem.innerHTML = custFname + ' ' + custLname;
}

const onEditCustomerOrderClickDoEditCustomerOrder = (event,id) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let customerId = document.getElementById('custId-'+id).innerText;
    let number = document.getElementById('custOrdNum-'+id).innerText;
    let description = document.getElementById('custOrdDesc-'+id).innerText;
    let dateCreated = document.getElementById('custOrdDate-'+id).innerText;
    let status = document.getElementById('custOrdStat-'+id).innerText;
    app.hideCustomerOrdersListSection('forEdit');
    let order = {};
    order.id = id;
    order.customerId = customerId;
    order.number = number;
    order.description = description;
    order.dateCreated = dateCreated;
    order.status = status;
    app.customerOrdersEdit.editOrder(order);
}

const onDeleteCustomerOrderClickDoDeleteCustomerOrder = (event,id) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let customerId = document.getElementById('custId-'+id).innerText;
    let number = document.getElementById('custOrdNum-'+id).innerText;
    let description = document.getElementById('custOrdDesc-'+id).innerText;
    let dateCreated = document.getElementById('custOrdDate-'+id).innerText;
    let status = document.getElementById('custOrdStat-'+id).innerText;
    app.hideCustomerOrdersListSection('forDelete');
    let order = {};
    order.id = id;
    order.customerId = customerId;
    order.number = number;
    order.description = description;
    order.dateCreated = dateCreated;
    order.status = status;
    app.customerOrdersDelete.deleteOrder(order);
}

const onCancelCustomerOrdersOperationBtnClickDoCancelCustomerOrdersOperation = (event,what) => {
    event.preventDefault();
    app.alerts.hideMessage();
    if (what !== 'Back') {
        app.alerts.displayMessage('warning', '"' + what + '" Canceled');
    }
    customerOverallOrdersSectionElem.style.display = 'block';
    customerOverallSectionElem.style.display = 'none';
    showCustomerOrdersListSection();
}

app.showCustomerListSection = showCustomerListSection;
app.hideCustomerListSection = hideCustomerListSection;
app.showCustomerOrdersListSection = showCustomerOrdersListSection;
app.hideCustomerOrdersListSection = hideCustomerOrdersListSection;

window.onload = () => {
    app.customerList.getCustomerList();
}