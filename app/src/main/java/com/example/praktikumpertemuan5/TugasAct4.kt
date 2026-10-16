package com.example.praktikumpertemuan5

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.time.Duration.Companion.seconds

@Composable
fun TugasAct4Screen(modifier: Modifier) {
    // State variables for form fields and dialog visibility
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var isAndroidSelected by remember { mutableStateOf(false) }
    var isUiUxSelected by remember { mutableStateOf(false) }
    var isWebSelected by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var isExpExpanded by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    val expPemula = stringResource(id = R.string.exp_pemula)
    val expMenengah = stringResource(id = R.string.exp_menengah)
    val expAhli = stringResource(id = R.string.exp_ahli)
    val expOptions = remember(expPemula, expMenengah, expAhli) {
        listOf(expPemula, expMenengah, expAhli)
    }


    LaunchedEffect(showDialog) {
        if (showDialog) {
            delay(5.seconds)
            showDialog = false
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            formatter.timeZone = TimeZone.getTimeZone("UTC")
                            tglLahir = formatter.format(Date(millis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text(stringResource(id = R.string.btn_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(id = R.string.btn_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Root container for Scaffold content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Image(
                painter = painterResource(id = R.drawable.bg_tampilan),
                contentDescription = stringResource(id = R.string.desc_header_bg),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.header_height)),
                contentScale = ContentScale.Crop
            )
            // Floating Box component floating on top of Scaffold background
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(id = R.dimen.card_top_offset)),
                shape = RoundedCornerShape(
                    topStart = dimensionResource(id = R.dimen.card_corner_top),
                    topEnd = dimensionResource(id = R.dimen.card_corner_top),
                ),
                color = colorResource(id = R.color.white),
                shadowElevation = dimensionResource(id = R.dimen.card_elevation)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.card_padding))
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Form Title
                    Text(
                        text = stringResource(id = R.string.form_title),
                        fontSize = dimensionResource(id = R.dimen.text_size_title).value.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.purple_title),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra_small)))

                    // Form Subtitle
                    Text(
                        text = stringResource(id = R.string.form_subtitle),
                        fontSize = dimensionResource(id = R.dimen.text_size_subtitle).value.sp,
                        color = colorResource(id = R.color.gray_subtitle),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_large)))


                    val fieldShape = RoundedCornerShape(dimensionResource(id = R.dimen.input_corner_radius))
                    val textFieldColors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(id = R.color.purple_title),
                        unfocusedBorderColor = colorResource(id = R.color.gray_border)
                    )
                    // Row 1: Nama Depan & Nama Belakang
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_between_fields))
                    )
                    {
                        OutlinedTextField(
                            value = namaDepan,
                            onValueChange = { namaDepan = it },
                            label = { Text(stringResource(id = R.string.hint_nama_depan)) },
                            modifier = Modifier.weight(1f),
                            shape = fieldShape,
                            colors = textFieldColors,
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = namaBelakang,
                            onValueChange = { namaBelakang = it },
                            label = { Text(stringResource(id = R.string.hint_nama_belakang)) },
                            modifier = Modifier.weight(1f),
                            shape = fieldShape,
                            colors = textFieldColors,
                            singleLine = true
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_between_fields)))

                    // Row 2: Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(id = R.string.hint_email)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = fieldShape,
                        colors = textFieldColors,
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_between_fields)))

                    // Row 3: Tgl Lahir & Experience
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_between_fields))
                    )
                    {
                        // Tgl Lahir (Clickable Field to open DatePicker)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) { showDatePicker = true }
                        ) {
                            OutlinedTextField(
                                value = tglLahir,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(stringResource(id = R.string.hint_tgl_lahir)) },
                                trailingIcon = {
                                    IconButton(onClick = { showDatePicker = true }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_calendar),
                                            contentDescription = stringResource(id = R.string.desc_calendar_icon)
                                        )
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = fieldShape,
                                colors = textFieldColors,
                                singleLine = true
                            )
                        }
                        // Experience (Dropdown Selection)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) { isExpExpanded = true }
                        ) {
                            OutlinedTextField(
                                value = experience,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(stringResource(id = R.string.hint_experience)) },
                                trailingIcon = {
                                    IconButton(onClick = { isExpExpanded = true }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                                            contentDescription = stringResource(id = R.string.desc_dropdown_icon)
                                        )
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = fieldShape,
                                colors = textFieldColors,
                                singleLine = true
                            )

                            DropdownMenu(
                                expanded = isExpExpanded,
                                onDismissRequest = { isExpExpanded = false }
                            ) {
                                expOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(text = option) },
                                        onClick = {
                                            experience = option
                                            isExpExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_between_sections)))


                        Text(
                            text = stringResource(id = R.string.label_pilih_skill),
                            fontSize = dimensionResource(id = R.dimen.text_size_label).value.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.text_black),
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_small)))

                        // Checkboxes Row: Android, UI/UX, Web
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        )   {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Checkbox(
                                    checked = isAndroidSelected,
                                    onCheckedChange = { isAndroidSelected = it },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(id = R.color.purple_title)
                                    )
                                )
                                Text(
                                    text = stringResource(id = R.string.skill_android),
                                    fontSize = dimensionResource(id = R.dimen.text_size_subtitle).value.sp,
                                    color = colorResource(id = R.color.text_dark_gray)
                                )
                            }

                            // Skill 2: UI/UX
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Checkbox(
                                    checked = isUiUxSelected,
                                    onCheckedChange = { isUiUxSelected = it },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(id = R.color.purple_title)
                                    )
                                )
                                Text(
                                    text = stringResource(id = R.string.skill_uiux),
                                    fontSize = dimensionResource(id = R.dimen.text_size_subtitle).value.sp,
                                    color = colorResource(id = R.color.text_dark_gray)
                                )
                            }
                            // Skill 3: Web
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Checkbox(
                                    checked = isWebSelected,
                                    onCheckedChange = { isWebSelected = it },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(id = R.color.purple_title)
                                    )
                                )
                                Text(
                                    text = stringResource(id = R.string.skill_web),
                                    fontSize = dimensionResource(id = R.dimen.text_size_subtitle).value.sp,
                                    color = colorResource(id = R.color.text_dark_gray)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_large)))
                        Button(
                            onClick = { showDialog = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.button_height)),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.gray_button_bg),
                                contentColor = colorResource(id = R.color.gray_button_text)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.btn_proceed),
                                fontSize = dimensionResource(id = R.dimen.text_size_button).value.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.button_height)),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.gray_button_bg),
                    contentColor = colorResource(id = R.color.gray_button_text)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.btn_proceed),
                    fontSize = dimensionResource(id = R.dimen.text_size_button).value.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

val namaLengkap = "$namaDepan $namaBelakang".trim()
Row(verticalAlignment = Alignment.CenterVertically) {
    Text(
        text = stringResource(id = R.string.label_nama_lengkap),
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.white),
        fontSize = dimensionResource(id = R.dimen.text_size_label).value.sp
    )
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.margin_small)))
    Text(
        text = namaLengkap.ifEmpty { stringResource(id = R.string.skill_none) },
        color = colorResource(id = R.color.white),
        fontSize = dimensionResource(id = R.dimen.text_size_input).value.sp
    )
}

Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_small)))


