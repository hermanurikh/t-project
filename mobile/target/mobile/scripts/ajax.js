/**
 * Created by german on 21.11.14.
 */

function getOptions(id) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    vds1.show();
    vds2.show();
    var div = $("#bigOptionDiv");
    div.hide();
    setTimeout(doAjax(id), 1000)
}
function getOptionsForOption(id) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    vds1.show();
    vds2.show();
    setTimeout(getOptionsTogether(id), 1000);
    setTimeout(getOptionsIncompatible(id), 1000);
    var div = $("#bigOptionDiv");
    div.show();
}



function doAjax (tariff_id) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    $.ajax({
        url: 'cp_employee_get_options_for_tariff/' + tariff_id,
        type: 'GET',
        success: function (data) {
            var bigDiv1 = $("#list_database2");
            var bigDiv = $("#optionTable tbody");
            bigDiv.empty();
            bigDiv.append(
                    "<tr class='ui-table-header' id = 'options-header'>" +
                    "<th class='header_s_checkbox' width='12' align='center'></th>" +
                    "<th class='header_s' style='width:150px;' id='table_header_database'>Опция</th>" +
                    "<th class='header_s' style='width:100px;' id='table_header_type'>Цена</th>" +
                    "<th class='header_s' style='width:100px;' id='table_header_point_access'>Цена подключения</th>" +
                    "</tr>"
            );
            data.forEach(function (elem, index, array) {
                bigDiv.append(
                        "<tr name='trow' class='ui-table-data-row ui-state-even ui-selected' onclick=getOptionsForOption(" + elem.id + ")>" +
                        "<td name='tcell' class='simplecell_checkbox' align='left'>" +
                        "<input type='checkbox' name='cb' id=" + elem.id + " value=" + elem.id + "></td>" +
                        "<td class='simplecell' name='tcell' style='vertical-align: top; width: 150px'><span>" + elem.name + "</span><br></td>" +
                        "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.price + "</span></td>" +
                        "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.initialPrice + "</span></td>" +
                        "</tr>");
            });
            bigDiv1.show();
            vds1.hide();
            vds2.hide();
        }
    });
}
function getOptionsTogether (id) {
    $.ajax({
        url: 'cp_employee_get_optionsTogether_for_option/' + id,
        type: 'GET',
        success: function (data) {
            var bigDiv = $("#optionTogetherTable tbody");
            bigDiv.empty();
            bigDiv.append(
                    "<tr class='ui-table-header'>" +
                    "<th class='header_s' style='width:150px;' id='table_header_database'>Опция</th>" +
                    "<th class='header_s' style='width:100px;' id='table_header_type'>Цена</th>" +
                    "<th class='header_s' style='width:100px;' id='table_header_point_access'>Цена подключения</th>" +
                    "</tr>"
            );
            data.forEach(function (elem, index, array) {
                bigDiv.append(
                        "<tr name='trow' class='ui-table-data-row ui-state-even ui-selected'>" +
                        "<td class='simplecell' name='tcell' style='vertical-align: top; width: 150px'><span>" + elem.name + "</span><br></td>" +
                        "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.price + "</span></td>" +
                        "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.initialPrice + "</span></td>" +
                        "</tr>");
            });
        }
    });

}
function getOptionsIncompatible (id) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    $.ajax({
        url: 'cp_employee_get_optionsIncompatible_for_option/' + id,
        type: 'GET',
        success: function (data) {
            var bigDiv = $("#optionIncompatibleTable tbody");
            bigDiv.empty();
            bigDiv.append(
                    "<tr class='ui-table-header'>" +
                    "<th class='header_s' style='width:150px;' id='table_header_database'>Опция</th>" +
                    "<th class='header_s' style='width:100px;' id='table_header_type'>Цена</th>" +
                    "<th class='header_s' style='width:100px;' id='table_header_point_access'>Цена подключения</th>" +
                    "</tr>"
            );
            data.forEach(function (elem, index, array) {
                bigDiv.append(
                        "<tr name='trow' class='ui-table-data-row ui-state-even ui-selected'>" +
                        "<td class='simplecell' name='tcell' style='vertical-align: top; width: 150px'><span>" + elem.name + "</span><br></td>" +
                        "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.price + "</span></td>" +
                        "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.initialPrice + "</span></td>" +
                        "</tr>");
            });
            vds1.hide();
            vds2.hide();
        }
    });

}
