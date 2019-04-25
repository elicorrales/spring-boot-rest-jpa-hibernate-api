'use strict';

var customerDelete = {};


const onSubmitDeleteCustomerBtnClickDoSubmitDeleteCustomer = (event) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let id    = document.getElementById('deleteIdInput').value;
    let fname = document.getElementById('deleteFnameInput').value;
    let lname = document.getElementById('deleteLnameInput').value;
    axios.delete('/customer/' + id)
    .then(
        result => {
            app.alerts.displayMessage('success','Customer ' + fname + ' ' + lname + ' deleted.');
            app.showCustomerListSection();
        }
    )
    .catch(
        error => {
            app.utils.displayError(error);
        }
    );

}

const deleteCustomer = (customer) => {
    document.getElementById('deleteIdInput').value = customer.id;
    document.getElementById('deleteFnameInput').value = customer.fname;
    document.getElementById('deleteLnameInput').value = customer.lname;
    document.getElementById('deleteEmailInput').value = customer.email;
}


customerDelete.deleteCustomer = deleteCustomer;
app.customerDelete = customerDelete;