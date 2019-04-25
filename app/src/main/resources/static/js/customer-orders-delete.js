'use strict';

var customerOrdersDelete = {};


const onSubmitDeleteCustomerOrderBtnClickDoSubmitDeleteCustomerOrder = (event) => {
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

const deleteOrder = (order) => {
    document.getElementById('deleteIdInput').value = order.id;
    document.getElementById('deleteNumberInput').value = order.fname;
    document.getElementById('deleteDescriptionInput').value = order.lname;
    document.getElementById('deleteDateOrderedInput').value = order.email;
    document.getElementById('deleteStatusInput').value = order.email;
}


customerOrdersDelete.deleteOrder = deleteOrder;
app.customerOrdersDelete = customerOrdersDelete;