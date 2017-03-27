function maxLength(field,maxlimit) {
	if (field.value.length > maxlimit)
		field.value = field.value.substring(0, maxlimit);
}

function apenasNumero(evt) {
	evt = (evt) ? evt : window.event;
			var charCode = (evt.which) ? evt.which : evt.keyCode;
					if (charCode > 31 && (charCode < 48 || charCode > 57)) {
						return false;
					}
	return true;
}




/**
 * Função que permite apenas números como entrada em campos input text.
 * onkeypress="return operationonly(this, event)"
 */
function operationonly(myfield, e, dec){
	var key;
	var keychar;

	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;

	keychar = String.fromCharCode(key);

	// control keys
	if ((key==null) || (key==0) || (key==8) || 
			(key==9) || (key==13) || (key==27) )
		return true;
	// operations
	else if ((("/*-+").indexOf(keychar) > -1))
		return true;
	else
		return false;
}

/**
 * Função que permite apenas números como entrada em campos input text.
 * onkeypress="return numbersonly(this, event)"
 */
function numbersonly(myfield, e, dec){
	var key;
	var keychar;

	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;

	keychar = String.fromCharCode(key);

	// control keys
	if ((key==null) || (key==0) || (key==8) || 
			(key==9) || (key==13) || (key==27) )
		return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
		return true;

	// decimal point jump
	else if (dec && (keychar == ".")){
		myfield.form.elements[dec].focus();
		return false;
	}else
		return false;
}

/* Macara Numerio */
function formatarNumerico(src, mask, e){	
	var tecla;
	var isNS4 = (navigator.appName=="Netscape");
	if(!isNS4){
		tecla = e.keyCode;
	} else {
		tecla = e.which;
	}
	if (tecla > 31 && (tecla < 48 || tecla > 57)) {
		if (tecla != 8) {
			tecla = 0;
			return false;
		}else{
			return true;
		}
	} else {
		if (tecla != 8){
			var i = src.value.length;
			var saida = mask.substring(0,1);
			var texto = mask.substring(i);
			if (texto.substring(0,1) != saida){
				src.value += texto.substring(0,1); 
			}
			return true;
		}
	}	  
}
/* fim Mascara Numerico */

function entsub(event,botao) {
	var tecla;
	var isNS4 = (navigator.appName=="Netscape");
	if(!isNS4){
		tecla = event.keyCode;
	} else {
		tecla = event.which;
	}
	if (tecla == 13){
		document.getElementById(botao).onclick();
		return false;
	}else{
		return true;}
}

// funcao max length para text area
function ismaxlength(obj, tamanho, e){
	var mlength=tamanho;
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
}
// / fim

function blockKey(pObj, e, pLength){
	var key;
	var keychar;
	var length = pLength;
	var obj = pObj;

	if(obj.getAttribute && obj.value.length < length)
		return true;
	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;

	keychar = String.fromCharCode(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) )
		return true;
	return false;
}


function setaFoco(elemento,e)  {  
	var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;  
	if (keyCode == 13) {  
		var i;  
		for (i = 0; i < elemento.form.elements.length; i++)  
			if (elemento == elemento.form.elements[i])  
				break;  
		i = (i + 1) % elemento.form.elements.length;  
		elemento.form.elements[i].focus();  
		event.preventDefault();  
		return false;  
	}  
	return false;  
}  
function somente_txt(par_tecla){
         var var_tecla = par_tecla.keyCode ? par_tecla.keyCode : par_tecla.which;
         /* Tecla Backspace */
         if (var_tecla == 8)
                  {return true;}
         /* Tecla Space */
         if (var_tecla == 32)
                  {return true;}
         /* Teclas a-z e A-Z */
         if ((var_tecla > 64 && var_tecla < 91) || (var_tecla > 96 && var_tecla < 123))
                  {return true;}
         /* Teclas acentuadas e cidilha */
         if ((var_tecla > 191 && var_tecla < 221) || (var_tecla > 223 && var_tecla < 253))
                  {return true;}
         return false;
}
// mascara de cpf e cnpj
function mascaraTexto(evento, mascara){  
    
	   var campo, valor, i, tam, caracter;  
	     
	   if (document.all) // Internet Explorer
	      campo = evento.srcElement;  
	   else // Nestcape, Mozzila
	       campo= evento.target;  
	         
	   valor = campo.value;  
	   tam = valor.length;  
	     
	   for(i=0;i<mascara.length;i++){  
	      caracter = mascara.charAt(i);  
	      if(caracter!="9")   
	         if(i<tam & caracter!=valor.charAt(i))  
	            campo.value = valor.substring(0,i) + caracter + valor.substring(i,tam);  
	                 
	   }  
	  
	} 

function Formatadata(Campo, teclapres){
	var tecla = teclapres.keyCode;
	var vr = new String(Campo.value);
	vr = vr.replace("/", "");
	vr = vr.replace("/", "");
	vr = vr.replace("/", "");
	tam = vr.length + 1;
	if (tecla != 8 && tecla != 8)
	{
		if (tam > 0 && tam < 2)
			Campo.value = vr.substr(0, 2) ;
		if (tam > 2 && tam < 4)
			Campo.value = vr.substr(0, 2) + '/' + vr.substr(2, 2);
		if (tam > 4 && tam < 7)
			Campo.value = vr.substr(0, 2) + '/' + vr.substr(2, 2) + '/' + vr.substr(4, 7);
	}
}
// adiciona mascara ao telefone
function mascara(src, mask){
	var i = src.value.length;
	var saida = mask.substring(1,2);
	var texto = mask.substring(i)
	if (texto.substring(0,1) != saida)
	{
	src.value += texto.substring(0,1);
	}
	} 



 
 

