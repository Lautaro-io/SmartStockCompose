package com.chelo.smartstock.ui.features.mainscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chelo.smartstock.data.entities.BranchEntity
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.WhiteText
import com.chelo.smartstock.viewmodel.BranchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchSection(
    modifier: Modifier = Modifier,
    branchViewModel: BranchViewModel,
    onButtonClick: () -> Unit,
    onAllSelect:() -> Unit?,
    onValueChange: (BranchEntity) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ExposedDropdownMenuBox(
            expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            OutlinedTextField(
                value = branchViewModel.selectedBranch?.branchName ?: "All",
                onValueChange = {},
                readOnly = true,
                label = { Text("Sucursales") },
                shape = RoundedCornerShape(32.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BackgroundColor,
                    focusedLabelColor = BackgroundColor
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded,
                onDismissRequest = { expanded = false },
            ) {
                DropdownMenuItem(
                    text = { Text("All") },
                    onClick = {
                        onAllSelect()
                        branchViewModel.selectedBranch = null
                        expanded = false
                    },

                    )
                branchViewModel.allBranches.collectAsState(emptyList()).value.forEach {
                    DropdownMenuItem(
                        text = { Text(it.branchName) },
                        onClick = {
                            onValueChange(it)
                            branchViewModel.selectBranch(it)
                            expanded = false
                        }
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = onButtonClick,
            modifier = Modifier
                .padding(end = 32.dp),
            containerColor = WhiteText,
            contentColor = BackgroundColor,
            elevation = FloatingActionButtonDefaults.elevation(
                4.dp,
            )

        ) {
            Icon(Icons.Default.Add, contentDescription = "")
        }
    }

}