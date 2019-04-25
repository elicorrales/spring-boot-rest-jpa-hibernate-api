'use strict';

var customerOrdersDelete = {};


const onSubmitDeleteCustomerOrderBtnClickDoSubmitDeleteCustomerOrder = (event) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let id    = document.getElementById('deleteOrderIdInput').value;
    let number = document.getElementById('deleteOrderNumberInput').value;
    let description = document.getElementById('deleteDescriptionInput').value;
    axios.delete('/order/' + id)
    .then(
        result => {
            app.alerts.displayMessage('success','Order ' + number + '<br/>' + description + ' deleted.');
            app.showCustomerOrdersListSection();
        }
    )
    .catch(
        error => {
            app.utils.displayError(error);
        }
    );

}

const deleteOrder = (order) => {
    document.getElementById('deleteOrderIdInput').value = order.id;
    document.getElementById('deleteOrderNumberInput').value = order.number;
    document.getElementById('deleteDescriptionInput').value = order.description;
    document.getElementById('deleteDateOrderedInput').value = order.dateOrdered;
    document.getElementById('deleteStatusInput').value = order.status;
}


customerOrdersDelete.deleteOrder = deleteOrder;
app.customerOrdersDelete = customerOrdersDelete;