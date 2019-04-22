'use strict';

var customerDelete = {};


const onSubmitDeleteCustomerBtnClickDoSubmitDeleteCustomer = (event) => {
    event.preventDefault();
    let id    = document.getElementById('deleteIdInput').value;
    let fname = document.getElementById('deleteFnameInput').value;
    let lname = document.getElementById('deleteLnameInput').value;
    axios.delete('/customers/customer/' + id)
    .then(
        result => {
            app.alerts.displayMessage('success','Customer ' + fname + ' ' + lname + ' deleted.');
            app.showCustomerListSection();
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