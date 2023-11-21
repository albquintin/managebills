$(".open-modal").on("click", function(){
  var row = $(this).closest('tr');
  var billId = row.find("td:first-child");
  var orderNumber = row.find("td:nth-child(2)");
  var url = "/bills/bills/delete/" + billId.text();
  $("#modal-content").append("¿Desea borrar de manera definitiva la factura: \'" + orderNumber.text() + "\'?");
  $(".delete-button").attr("href", url);
  $(".modal").addClass("is-active");
});
$(".close-modal").on("click", function(){
  $(".modal").removeClass("is-active");
  $("#modal-content").empty();
});
$(document).ready(function()  {
  $('#billsTable').DataTable();
});
$('#billsTable').DataTable({
    'aoColumnDefs': [{
        'bSortable': false,
        'aTargets': [-1]
    }]
});