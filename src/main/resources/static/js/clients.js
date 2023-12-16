$(document).ready(function()  {
    $('#clientsTable').DataTable();
});
$('#clientsTable').DataTable({
    'order': [[4, 'desc']],
    'aoColumnDefs': [{
        'bSortable': false,
        'aTargets': [-1]
    }]
});