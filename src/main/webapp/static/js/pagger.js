/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function pagger_init(id,pageindex,total,gap)
{
    var container = document.getElementById(id);
    var content = "";
    if(pageindex > gap+1 )
        content += '<a href="?page=1">First</a>';
    if(pageindex - gap >2)
        content += '...';
    for (var i = pageindex - gap; i < pageindex; i++) {
        if(i>0)
            content += '<a href="?page='+i+'">'+i+'</a>';
    }
    content += '<span>'+pageindex+'</span>';
    for (var i = pageindex +1; i <= pageindex+gap; i++) {
        if(i <= total )
            content += '<a href="?page='+i+'">'+i+'</a>';
    }
    if(pageindex < total - gap -1)
        content += '...';
    if(pageindex < total - gap )
        content += '<a href="?page='+total+'">Last</a>';
    container.innerHTML= content;
} 