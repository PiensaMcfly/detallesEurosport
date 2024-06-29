
$(function() {
    var estados = [
        /*[[${#lists.array('PENDIENTE', 'APROBADO', 'CANCELADO' , 'LISTO')}]]*/
        /*[[${estados}]]*/
        [[${estados.stream().map(est -> est.name()).collect(Collectors.toList())}]]
    ];

    $("#estado").autocomplete({
        source: estados
    });
});
