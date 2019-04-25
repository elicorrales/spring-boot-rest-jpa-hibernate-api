'use strict';

var customerOrdersEdit = {};


const onSubmitUpdateCustomerOrderBtnClickDoSubmitUpdateCustomerOrder = (event) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let id    = document.getElementById('editIdInput').value;
    let fname = document.getElementById('editFnameInput').value;
    let lname = document.getElementById('editLnameInput').value;
    let email = document.getElementById('editEmailInput').value;
    axios.put('/customer', { id, fname, lname, email })
    .then(
        result => {
            app.alerts.displayMessage('success','Customer ' + fname + ' ' + lname + ' updated.');
            app.showCustomerListSection();
        }
    )
    .catch(
        error => {
            app.utils.displayError(error);
        }
    );

}

const editOrder = (order) => {
    document.getElementById('editIdInput').value = order.id;
    document.getElementById('editFnameInput').value = order.fname;
    document.getElementById('editLnameInput').value = order.lname;
    document.getElementById('editEmailInput').value = order.email;
}


customerOrdersEdit.editOrder = editOrder;
app.customerOrdersEdit = customerOrdersEdit;