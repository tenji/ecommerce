<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%@ include file="/common/customTaglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${path }/assets/admin/js/plugin/jquery/jquery-2.0.3.js" type="text/javascript"></script>
<script type="text/javascript">
	function add() {
		$("<p>added</p>").appendTo($("#add"));
	}
</script>
</head>
<body>
<p>HAHA, this is a test file!</p>
<div id="add">
	
</div>
<form name="survey" action="EDC_Wear_2014_1_Handler.php" method="POST">

男：<input type="radio" name="sex" value="0" onclick="add();" />
女：<input type="radio" name="sex" value="1" />

<input type="hidden" name="survey_id" value="EDC_Wear_2014_1">
<input type="hidden" name="survey_target" value="">
<input type="hidden" name="xx_lang" value="EN">

<h4>Please enter your email address and other contact information so that we can notify you if you win $500 in the drawing!</h4>
<table>
<tr><td width="100">Email Address</td><td><input type="text" name="xx_email" maxlength="64" onBlur="getPanelData(this.value);">
<span id="fillform_notice" style="visibility: collapse"><em>Please verify that all your answers to the standard questions below are current.</em></span></td></tr>
<tr><td width="100">First Name</td><td><input type="text" name="xx_fname" maxlength="64"></td></tr>
<tr><td width="100">Last Name</td><td><input type="text" name="xx_lname" maxlength="64"></td></tr>
<tr><td width="100">Company</td><td><input type="text" name="xx_co" maxlength="64"></td></tr>
<tr><td width="100">Country</td><td><select name="xx_cnt">
	<option value="">---</option>
	<option value="AF">Afghanistan</option>
	<option value="AX">Aland Islands</option>
	<option value="AL">Albania</option>
	<option value="DZ">Algeria</option>
	<option value="AS">American Samoa</option>
	<option value="AD">Andorra</option>
	<option value="AO">Angola</option>
	<option value="AI">Anguilla</option>
	<option value="AG">Antigua and Barbuda</option>
	<option value="AR">Argentina</option>
	<option value="AM">Armenia</option>
	<option value="AW">Aruba</option>
	<option value="AU">Australia</option>
	<option value="AT">Austria</option>
	<option value="AZ">Azerbaijan</option>
	<option value="BS">Bahamas</option>
	<option value="BH">Bahrain</option>
	<option value="BD">Bangladesh</option>
	<option value="BB">Barbados</option>
	<option value="BY">Belarus</option>
	<option value="BE">Belgium</option>
	<option value="BZ">Belize</option>
	<option value="BJ">Benin</option>
	<option value="BM">Bermuda</option>
	<option value="BT">Bhutan</option>
	<option value="BO">Bolivia</option>
	<option value="BA">Bosnia and Herzegovina</option>
	<option value="BW">Botswana</option>
	<option value="BV">Bouvet Island</option>
	<option value="BR">Brazil</option>
	<option value="IO">British Indian Ocean Territory</option>
	<option value="BN">Brunei Darussalam</option>
	<option value="BG">Bulgaria</option>
	<option value="BF">Burkina Faso</option>
	<option value="BI">Burundi</option>
	<option value="KH">Cambodia</option>
	<option value="CM">Cameroon</option>
	<option value="CA">Canada</option>
	<option value="CV">Cape Verde</option>
	<option value="KY">Cayman Islands</option>
	<option value="CF">Central African Republic</option>
	<option value="TD">Chad</option>
	<option value="CL">Chile</option>
	<option value="CN">China</option>
	<option value="CX">Christmas Island</option>
	<option value="CC">Cocos (Keeling) Islands</option>
	<option value="CO">Colombia</option>
	<option value="KM">Comoros</option>
	<option value="CG">Congo</option>
	<option value="CK">Cook Islands</option>
	<option value="CR">Costa Rica</option>
	<option value="CI">Cote D&apos;Ivoire (Ivory Coast)</option>
	<option value="HR">Croatia (Hrvatska)</option>
	<option value="CU">Cuba</option>
	<option value="CY">Cyprus</option>
	<option value="CZ">Czech Republic</option>
	<option value="CD">Democratic Republic of the Congo</option>
	<option value="DK">Denmark</option>
	<option value="DJ">Djibouti</option>
	<option value="DM">Dominica</option>
	<option value="DO">Dominican Republic</option>
	<option value="TP">East Timor</option>
	<option value="EC">Ecuador</option>
	<option value="EG">Egypt</option>
	<option value="SV">El Salvador</option>
	<option value="GQ">Equatorial Guinea</option>
	<option value="ER">Eritrea</option>
	<option value="EE">Estonia</option>
	<option value="ET">Ethiopia</option>
	<option value="FK">Falkland Islands (Malvinas)</option>
	<option value="FO">Faroe Islands</option>
	<option value="FM">Federated States of Micronesia</option>
	<option value="FJ">Fiji</option>
	<option value="FI">Finland</option>
	<option value="FR">France</option>
	<option value="GF">French Guiana</option>
	<option value="PF">French Polynesia</option>
	<option value="TF">French Southern Territories</option>
	<option value="GA">Gabon</option>
	<option value="GM">Gambia</option>
	<option value="GE">Georgia</option>
	<option value="DE">Germany</option>
	<option value="GH">Ghana</option>
	<option value="GI">Gibraltar</option>
	<option value="GR">Greece</option>
	<option value="GL">Greenland</option>
	<option value="GD">Grenada</option>
	<option value="GP">Guadeloupe</option>
	<option value="GU">Guam</option>
	<option value="GT">Guatemala</option>
	<option value="GN">Guinea</option>
	<option value="GW">Guinea-Bissau</option>
	<option value="GY">Guyana</option>
	<option value="HT">Haiti</option>
	<option value="HM">Heard Island and McDonald Islands</option>
	<option value="HN">Honduras</option>
	<option value="HK">Hong Kong</option>
	<option value="HU">Hungary</option>
	<option value="IS">Iceland</option>
	<option value="IN">India</option>
	<option value="ID">Indonesia</option>
	<option value="IR">Iran</option>
	<option value="IQ">Iraq</option>
	<option value="IE">Ireland</option>
	<option value="IL">Israel</option>
	<option value="IT">Italy</option>
	<option value="JM">Jamaica</option>
	<option value="JP">Japan</option>
	<option value="JO">Jordan</option>
	<option value="KZ">Kazakhstan</option>
	<option value="KE">Kenya</option>
	<option value="KI">Kiribati</option>
	<option value="KP">Korea (North)</option>
	<option value="KR">Korea (South)</option>
	<option value="KW">Kuwait</option>
	<option value="KG">Kyrgyzstan</option>
	<option value="LA">Laos</option>
	<option value="LV">Latvia</option>
	<option value="LB">Lebanon</option>
	<option value="LS">Lesotho</option>
	<option value="LR">Liberia</option>
	<option value="LY">Libya</option>
	<option value="LI">Liechtenstein</option>
	<option value="LT">Lithuania</option>
	<option value="LU">Luxembourg</option>
	<option value="MO">Macao</option>
	<option value="MK">Macedonia</option>
	<option value="MG">Madagascar</option>
	<option value="MW">Malawi</option>
	<option value="MY">Malaysia</option>
	<option value="MV">Maldives</option>
	<option value="ML">Mali</option>
	<option value="MT">Malta</option>
	<option value="MH">Marshall Islands</option>
	<option value="MQ">Martinique</option>
	<option value="MR">Mauritania</option>
	<option value="MU">Mauritius</option>
	<option value="YT">Mayotte</option>
	<option value="MX">Mexico</option>
	<option value="MD">Moldova</option>
	<option value="MC">Monaco</option>
	<option value="MN">Mongolia</option>
	<option value="MS">Montserrat</option>
	<option value="MA">Morocco</option>
	<option value="MZ">Mozambique</option>
	<option value="MM">Myanmar</option>
	<option value="NA">Namibia</option>
	<option value="NR">Nauru</option>
	<option value="NP">Nepal</option>
	<option value="NL">Netherlands</option>
	<option value="AN">Netherlands Antilles</option>
	<option value="NC">New Caledonia</option>
	<option value="NZ">New Zealand (Aotearoa)</option>
	<option value="NI">Nicaragua</option>
	<option value="NE">Niger</option>
	<option value="NG">Nigeria</option>
	<option value="NU">Niue</option>
	<option value="NF">Norfolk Island</option>
	<option value="MP">Northern Mariana Islands</option>
	<option value="NO">Norway</option>
	<option value="OM">Oman</option>
	<option value="PK">Pakistan</option>
	<option value="PW">Palau</option>
	<option value="PS">Palestinian Territory</option>
	<option value="PA">Panama</option>
	<option value="PG">Papua New Guinea</option>
	<option value="PY">Paraguay</option>
	<option value="PE">Peru</option>
	<option value="PH">Philippines</option>
	<option value="PN">Pitcairn</option>
	<option value="PL">Poland</option>
	<option value="PT">Portugal</option>
	<option value="PR">Puerto Rico</option>
	<option value="QA">Qatar</option>
	<option value="RE">Reunion</option>
	<option value="RO">Romania</option>
	<option value="RU">Russian Federation</option>
	<option value="RW">Rwanda</option>
	<option value="GS">S. Georgia and S. Sandwich Islands</option>
	<option value="SH">Saint Helena</option>
	<option value="KN">Saint Kitts and Nevis</option>
	<option value="LC">Saint Lucia</option>
	<option value="PM">Saint Pierre and Miquelon</option>
	<option value="VC">Saint Vincent and the Grenadines</option>
	<option value="WS">Samoa</option>
	<option value="SM">San Marino</option>
	<option value="ST">Sao Tome and Principe</option>
	<option value="SA">Saudi Arabia</option>
	<option value="SN">Senegal</option>
	<option value="CS">Serbia and Montenegro</option>
	<option value="SC">Seychelles</option>
	<option value="SL">Sierra Leone</option>
	<option value="SG">Singapore</option>
	<option value="SK">Slovakia</option>
	<option value="SI">Slovenia</option>
	<option value="SB">Solomon Islands</option>
	<option value="SO">Somalia</option>
	<option value="ZA">South Africa</option>
	<option value="ES">Spain</option>
	<option value="LK">Sri Lanka</option>
	<option value="SD">Sudan</option>
	<option value="SR">Suriname</option>
	<option value="SJ">Svalbard and Jan Mayen</option>
	<option value="SZ">Swaziland</option>
	<option value="SE">Sweden</option>
	<option value="CH">Switzerland</option>
	<option value="SY">Syria</option>
	<option value="TW">Taiwan</option>
	<option value="TJ">Tajikistan</option>
	<option value="TZ">Tanzania</option>
	<option value="TH">Thailand</option>
	<option value="TL">Timor-Leste</option>
	<option value="TG">Togo</option>
	<option value="TK">Tokelau</option>
	<option value="TO">Tonga</option>
	<option value="TT">Trinidad and Tobago</option>
	<option value="TN">Tunisia</option>
	<option value="TR">Turkey</option>
	<option value="TM">Turkmenistan</option>
	<option value="TC">Turks and Caicos Islands</option>
	<option value="TV">Tuvalu</option>
	<option value="UG">Uganda</option>
	<option value="UA">Ukraine</option>
	<option value="AE">United Arab Emirates</option>
	<option value="UK">United Kingdom</option>
	<option value="US">United States</option>
	<option value="UM">United States Minor Outlying Islands</option>
	<option value="UY">Uruguay</option>
	<option value="UZ">Uzbekistan</option>
	<option value="VU">Vanuatu</option>
	<option value="VA">Vatican City State (Holy See)</option>
	<option value="VE">Venezuela</option>
	<option value="VN">Vietnam</option>
	<option value="VG">Virgin Islands (British)</option>
	<option value="VI">Virgin Islands (U.S.)</option>
	<option value="WF">Wallis and Futuna</option>
	<option value="EH">Western Sahara</option>
	<option value="YE">Yemen</option>
	<option value="ZM">Zambia</option>
	<option value="ZW">Zimbabwe</option>
</select></td></tr>
</table>

<h4>If you have a friend you think would find this survey interesting, please enter his or her email address here.</h4>
<table><tr><td width="100">Friend</td><td><input type="text" name="xx_fr" maxlength="64"></td></tr></table>

<h4>Periodically we conduct focus groups or round-tables. If you would be interested in participating, please add your phone number here:</h4>
<table><tr><td width="100">Phone</td><td><input type="text" name="xx_phone" maxlength="64"> <em>(Write in a phone number if interested, otherwise leave blank)</em></td></tr></table>
<br /><hr />

<h4>Do you know anybody who has a wearable computing device?</h4>
<p><em>(Check one only)</em></p>
<div class="divinp"><input type="radio" name="q1" id="q1a" value="1" />&nbsp;<label for="q1a">Yes</label></div>
<div class="divinp"><input type="radio" name="q1" id="q1b" value="2" />&nbsp;<label for="q1b">No</label></div>
<br /><hr />

<h4>Do you, personally, own a wearable?</h4>
<p><em>(Check one only)</em></p>
<div class="divinp"><input type="radio" name="q2" id="q2a" value="1" />&nbsp;<label for="q2a">Yes</label></div>
<div class="divinp"><input type="radio" name="q2" id="q2b" value="2" />&nbsp;<label for="q2b">No</label></div>
<br /><hr />

<h4>What kinds of wearables?</h4>
<p><em>(Check all that apply)</em></p>
<div class="divinp"><input type="checkbox" name="q3a" id="q3a" value="1" />&nbsp;<label for="q3a">Glasses</label></div>
<div class="divinp"><input type="checkbox" name="q3b" id="q3b" value="1" />&nbsp;<label for="q3b">Clothing/Shoes</label></div>
<div class="divinp"><input type="checkbox" name="q3c" id="q3c" value="1" />&nbsp;<label for="q3c">Armbands</label></div>
<div class="divinp"><input type="checkbox" name="q3d" id="q3d" value="1" />&nbsp;<label for="q3d">Smartwatches</label></div>
<div class="divinp"><input type="checkbox" name="q3e" id="q3e" value="1" OnClick="toggleDynamic('q4',1);" />&nbsp;<label for="q3e">Other</label><span id="dynamicq4" style="display:none"> please specify: <input type="text" name="q4" id="q4" /></span></div>
<br /><hr />

<h4>Are you interested in developing apps for wearables?</h4>
<p><em>(Check one only)</em></p>
<div class="divinp"><input type="radio" name="q5" id="q5a" value="1" />&nbsp;<label for="q5a">I currently do this</label></div>
<div class="divinp"><input type="radio" name="q5" id="q5b" value="2" />&nbsp;<label for="q5b">I don't do this yet, but I'm very interested</label></div>
<div class="divinp"><input type="radio" name="q5" id="q5c" value="3" />&nbsp;<label for="q5c">I don't do this yet, but I'm somewhat interested</label></div>
<div class="divinp"><input type="radio" name="q5" id="q5d" value="4" />&nbsp;<label for="q5d">I don't do this, and I'm not interested</label></div>
<br /><hr />

<h4>Are wearables/will wearables be:</h4>
<p><em>(Check one only)</em></p>
<div class="divinp"><input type="radio" name="q6" id="q6a" value="1" />&nbsp;<label for="q6a">A primary focus of your work</label></div>
<div class="divinp"><input type="radio" name="q6" id="q6b" value="2" />&nbsp;<label for="q6b">One of many things you do for work</label></div>
<div class="divinp"><input type="radio" name="q6" id="q6c" value="3" />&nbsp;<label for="q6c">Just a project for a client</label></div>
<div class="divinp"><input type="radio" name="q6" id="q6d" value="4" />&nbsp;<label for="q6d">A project for school or academic work</label></div>
<div class="divinp"><input type="radio" name="q6" id="q6e" value="5" />&nbsp;<label for="q6e">Done outside of work</label></div>
<br /><hr />

<h4>What type of industry are you primarily targeting for your wearable technology?</h4>
<p><em>(Check up to 3)</em></p>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);" name="q7[]" id="q7a" value="1" />&nbsp;<label for="q7a">Sports</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);" name="q7[]" id="q7b" value="2" />&nbsp;<label for="q7b">Medicine / Health</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);" name="q7[]" id="q7c" value="3" />&nbsp;<label for="q7c">Logistics</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);" name="q7[]" id="q7d" value="4" />&nbsp;<label for="q7d">Productivity</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);" name="q7[]" id="q7e" value="5" />&nbsp;<label for="q7e">Security</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);" name="q7[]" id="q7f" value="6" />&nbsp;<label for="q7f">Retail</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);" name="q7[]" id="q7g" value="7" />&nbsp;<label for="q7g">Transportation</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);" name="q7[]" id="q7h" value="8" />&nbsp;<label for="q7h">Navigation/GPS</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);" name="q7[]" id="q7i" value="9" />&nbsp;<label for="q7i">Entertainment/Gaming</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q7',3,this);toggleDynamic('q8',1);" name="q7[]" id="q7j" value="10" />&nbsp;<label for="q7j">Other</label><span id="dynamicq8" style="display:none"> please specify: <input type="text" name="q8" id="q8" /></span></div>
<br /><hr />

<h4>How comfortable are you about your wearables sharing the following types of information with cloud services?</h4>
<p><em>(Check one per row)</em></p>
<table border="1" cellpadding="6">
<tr>
	<td class="divider">&nbsp;</td>
	<th class="divider">Very comfortable</th>
	<th class="divider">somewhat comfortable</th>
	<th class="divider">somewhat uncomfortable</th>
	<th class="divider">not at all comfortable</th>
</tr>
<tr bgcolor="#e6e6e6">
	<td>Location – GPS coordinates</td>
	<td align="center" OnClick="ClickCell('q9a1');"><input type="radio" name="q9a" id="q9a1" value="1" /><label for="q9a1"></label></td>
	<td align="center" OnClick="ClickCell('q9a2');"><input type="radio" name="q9a" id="q9a2" value="2" /><label for="q9a2"></label></td>
	<td align="center" OnClick="ClickCell('q9a3');"><input type="radio" name="q9a" id="q9a3" value="3" /><label for="q9a3"></label></td>
	<td align="center" OnClick="ClickCell('q9a4');"><input type="radio" name="q9a" id="q9a4" value="4" /><label for="q9a4"></label></td>
</tr>
<tr>
	<td>Location – Movement patterns</td>
	<td align="center" OnClick="ClickCell('q9b1');"><input type="radio" name="q9b" id="q9b1" value="1" /><label for="q9b1"></label></td>
	<td align="center" OnClick="ClickCell('q9b2');"><input type="radio" name="q9b" id="q9b2" value="2" /><label for="q9b2"></label></td>
	<td align="center" OnClick="ClickCell('q9b3');"><input type="radio" name="q9b" id="q9b3" value="3" /><label for="q9b3"></label></td>
	<td align="center" OnClick="ClickCell('q9b4');"><input type="radio" name="q9b" id="q9b4" value="4" /><label for="q9b4"></label></td>
</tr>
<tr bgcolor="#e6e6e6">
	<td>Web activity</td>
	<td align="center" OnClick="ClickCell('q9c1');"><input type="radio" name="q9c" id="q9c1" value="1" /><label for="q9c1"></label></td>
	<td align="center" OnClick="ClickCell('q9c2');"><input type="radio" name="q9c" id="q9c2" value="2" /><label for="q9c2"></label></td>
	<td align="center" OnClick="ClickCell('q9c3');"><input type="radio" name="q9c" id="q9c3" value="3" /><label for="q9c3"></label></td>
	<td align="center" OnClick="ClickCell('q9c4');"><input type="radio" name="q9c" id="q9c4" value="4" /><label for="q9c4"></label></td>
</tr>
<tr>
	<td>Personal communications (texts, emails, phone calls)</td>
	<td align="center" OnClick="ClickCell('q9d1');"><input type="radio" name="q9d" id="q9d1" value="1" /><label for="q9d1"></label></td>
	<td align="center" OnClick="ClickCell('q9d2');"><input type="radio" name="q9d" id="q9d2" value="2" /><label for="q9d2"></label></td>
	<td align="center" OnClick="ClickCell('q9d3');"><input type="radio" name="q9d" id="q9d3" value="3" /><label for="q9d3"></label></td>
	<td align="center" OnClick="ClickCell('q9d4');"><input type="radio" name="q9d" id="q9d4" value="4" /><label for="q9d4"></label></td>
</tr>
<tr bgcolor="#e6e6e6">
	<td>Biometric/physiological</td>
	<td align="center" OnClick="ClickCell('q9e1');"><input type="radio" name="q9e" id="q9e1" value="1" /><label for="q9e1"></label></td>
	<td align="center" OnClick="ClickCell('q9e2');"><input type="radio" name="q9e" id="q9e2" value="2" /><label for="q9e2"></label></td>
	<td align="center" OnClick="ClickCell('q9e3');"><input type="radio" name="q9e" id="q9e3" value="3" /><label for="q9e3"></label></td>
	<td align="center" OnClick="ClickCell('q9e4');"><input type="radio" name="q9e" id="q9e4" value="4" /><label for="q9e4"></label></td>
</tr>
<tr>
	<td>Purchases</td>
	<td align="center" OnClick="ClickCell('q9f1');"><input type="radio" name="q9f" id="q9f1" value="1" /><label for="q9f1"></label></td>
	<td align="center" OnClick="ClickCell('q9f2');"><input type="radio" name="q9f" id="q9f2" value="2" /><label for="q9f2"></label></td>
	<td align="center" OnClick="ClickCell('q9f3');"><input type="radio" name="q9f" id="q9f3" value="3" /><label for="q9f3"></label></td>
	<td align="center" OnClick="ClickCell('q9f4');"><input type="radio" name="q9f" id="q9f4" value="4" /><label for="q9f4"></label></td>
</tr>
<tr bgcolor="#e6e6e6">
	<td>Leisure activities</td>
	<td align="center" OnClick="ClickCell('q9g1');"><input type="radio" name="q9g" id="q9g1" value="1" /><label for="q9g1"></label></td>
	<td align="center" OnClick="ClickCell('q9g2');"><input type="radio" name="q9g" id="q9g2" value="2" /><label for="q9g2"></label></td>
	<td align="center" OnClick="ClickCell('q9g3');"><input type="radio" name="q9g" id="q9g3" value="3" /><label for="q9g3"></label></td>
	<td align="center" OnClick="ClickCell('q9g4');"><input type="radio" name="q9g" id="q9g4" value="4" /><label for="q9g4"></label></td>
</tr>
<tr>
	<td>Personal scheduling information</td>
	<td align="center" OnClick="ClickCell('q9h1');"><input type="radio" name="q9h" id="q9h1" value="1" /><label for="q9h1"></label></td>
	<td align="center" OnClick="ClickCell('q9h2');"><input type="radio" name="q9h" id="q9h2" value="2" /><label for="q9h2"></label></td>
	<td align="center" OnClick="ClickCell('q9h3');"><input type="radio" name="q9h" id="q9h3" value="3" /><label for="q9h3"></label></td>
	<td align="center" OnClick="ClickCell('q9h4');"><input type="radio" name="q9h" id="q9h4" value="4" /><label for="q9h4"></label></td>
</tr>
</table>
<br /><hr />

<h4>Would you rather:</h4>
<p><em>(Check one only)</em></p>
<div class="divinp"><input type="radio" name="q10" id="q10a" value="1" />&nbsp;<label for="q10a">Have the data created by your wearable device sent out through the Cloud</label></div>
<div class="divinp"><input type="radio" name="q10" id="q10b" value="2" />&nbsp;<label for="q10b">Have the wearable device connect to other devices via near field computing</label></div>
<div class="divinp"><input type="radio" name="q10" id="q10c" value="3" />&nbsp;<label for="q10c">Neither</label></div>
<br /><hr />

<h4>Are wearable devices a viable platform?</h4>
<p><em>(Check one only)</em></p>
<div class="divinp"><input type="radio" name="q11" id="q11a" value="1" />&nbsp;<label for="q11a">Yes, they are or will be</label></div>
<div class="divinp"><input type="radio" name="q11" id="q11b" value="2" />&nbsp;<label for="q11b">They likely are or will be</label></div>
<div class="divinp"><input type="radio" name="q11" id="q11c" value="3" />&nbsp;<label for="q11c">They likely aren't or won’t be</label></div>
<div class="divinp"><input type="radio" name="q11" id="q11d" value="4" />&nbsp;<label for="q11d">It isn't and won’t be</label></div>
<br /><hr />

<h4>What would make it easier to get people interested in developing wearable apps?</h4>
<p><em>(Check up to 3)</em></p>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q12',3,this);" name="q12[]" id="q12a" value="1" />&nbsp;<label for="q12a">Improve the hardware specs</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q12',3,this);" name="q12[]" id="q12b" value="2" />&nbsp;<label for="q12b">Make more viable commercial use cases</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q12',3,this);" name="q12[]" id="q12c" value="3" />&nbsp;<label for="q12c">More lucrative/greater market</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q12',3,this);" name="q12[]" id="q12d" value="4" />&nbsp;<label for="q12d">Better style/aesthetics</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q12',3,this);" name="q12[]" id="q12e" value="5" />&nbsp;<label for="q12e">Improved backend services</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q12',3,this);" name="q12[]" id="q12f" value="6" />&nbsp;<label for="q12f">Improved user interface</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q12',3,this);" name="q12[]" id="q12g" value="7" />&nbsp;<label for="q12g">Integrating with major mobile platforms</label></div>
<div class="divinp"><input type="checkbox"  onclick="limitTop('q12',3,this);toggleDynamic('q13',1);" name="q12[]" id="q12h" value="8" />&nbsp;<label for="q12h">Other</label><span id="dynamicq13" style="display:none"> please specify: <input type="text" name="q13" id="q13" /></span></div>
<br /><hr />

<h4>What, if any, connected device would you like to see in the future?</h4>
<p><em>(Please write in your answer)</em></p>
<input type="text" name="q14" id="q14" style="width:600px;" /><br /><br /><hr />

<!-- END: MAIN SURVEY -->

<center>Thank you, that completes our survey.</center>

<br />

<center>
  	<input type="submit" value="Submit Survey" id="Submit">
</center>

</form>
</body>
</html>