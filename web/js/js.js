/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$('body').scrollspy({taget:'#ta'})

function showMessage(){
    var message = document.getElementById("message");     
    if(message.value !== null &&  message.value!== ""){

        $("#exito").show(300);

    }else{
        $("#exito").hide(500);
    }
}

