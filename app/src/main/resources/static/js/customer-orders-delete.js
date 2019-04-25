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
    document.getElementById('deleteOrderNumberInput').value = order.number;
    document.getElementById('deleteDescriptionInput').value = order.description;
    document.getElementById('deleteDateOrderedInput').value = order.dateOrdered;
    document.getElementById('deleteStatusInput').value = order.status;
}


customerOrdersDelete.deleteOrder = deleteOrder;
app.customerOrdersDelete = customerOrdersDelete;