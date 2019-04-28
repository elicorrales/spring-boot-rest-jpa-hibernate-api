'use strict';

var customerEdit = {};


const onSubmitUpdateCustomerBtnClickDoSubmitUpdateCustomer = (event) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let id    = document.getElementById('editIdInput').value;
    let fname = document.getElementById('editFnameInput').value;
    let lname = document.getElementById('editLnameInput').value;
    let email = document.getElementById('editEmailInput').value;
    axios.put('/customers', { id, fname, lname, email })
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

const editCustomer = (customer) => {
    document.getElementById('editIdInput').value = customer.id;
    document.getElementById('editFnameInput').value = customer.fname;
    document.getElementById('editLnameInput').value = customer.lname;
    document.getElementById('editEmailInput').value = customer.email;
}


customerEdit.editCustomer = editCustomer;
app.customerEdit = customerEdit;