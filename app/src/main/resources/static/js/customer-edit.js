'use strict';

var customerEdit = {};


const onSubmitUpdateCustomerBtnClickDoSubmitUpdateCustomer = (event) => {
    event.preventDefault();
    let id    = document.getElementById('editIdInput').value;
    let fname = document.getElementById('editFnameInput').value;
    let lname = document.getElementById('editLnameInput').value;
    let email = document.getElementById('editEmailInput').value;
    axios.put('/customers/customer', { id, fname, lname, email })
    .then(
        result => {
            app.alerts.displayMessage('success','Customer ' + fname + ' ' + lname + ' updated.');
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

const editCustomer = (customer) => {
    document.getElementById('editIdInput').value = customer.id;
    document.getElementById('editFnameInput').value = customer.fname;
    document.getElementById('editLnameInput').value = customer.lname;
    document.getElementById('editEmailInput').value = customer.email;
}


customerEdit.editCustomer = editCustomer;
app.customerEdit = customerEdit;