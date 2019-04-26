'use strict';

var customerOrdersEdit = {};


const onSubmitUpdateCustomerOrderBtnClickDoSubmitUpdateCustomerOrder = (event) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let id    = document.getElementById('editOrderIdInput').value;
    let number = document.getElementById('editOrderNumberInput').value;
    let description = document.getElementById('editDescriptionInput').value;
    let dateCreated = document.getElementById('editDateCreatedInput').value;
    let status = document.getElementById('editStatusInput').value;
    axios.put('/customer/'+idOfCustomerSelectedFromList+'/order',
             { customerId:idOfCustomerSelectedFromList, id, number, description, dateCreated, status })
    .then(
        result => {
            app.alerts.displayMessage('success','Order ' + number + '<br/>' + description + ' updated.');
            app.showCustomerOrdersListSection();
        }
    )
    .catch(
        error => {
            app.utils.displayError(error);
        }
    );

}

const editOrder = (order) => {
    document.getElementById('editOrderIdInput').value = order.id;
    document.getElementById('editOrderNumberInput').value = order.number;
    document.getElementById('editDescriptionInput').value = order.description;
    document.getElementById('editDateCreatedInput').value = order.dateCreated;
    document.getElementById('editStatusInput').value = order.status;
}


customerOrdersEdit.editOrder = editOrder;
app.customerOrdersEdit = customerOrdersEdit;