$(".open-modal").on("click", function(){
  var row = $(this).closest('tr');
  var cashId = row.find("td:first-child");
  var url = "/cash/cash/delete/" + cashId.text();
  $("#modal-content").append("¿Desea borrar de manera definitiva el cobro?");
  $(".delete-button").attr("href", url);
  $(".modal").addClass("is-active");
});
$(".close-modal").on("click", function(){
  $(".modal").removeClass("is-active");
  $("#modal-content").empty();
});
$(document).ready(function()  {
  $('#cashTable').DataTable();
});
$('#cashTable').DataTable({
    'order': [[0, 'desc']],
    'aoColumnDefs': [{
        'bSortable': false,
        'aTargets': [-1]
    }]
});