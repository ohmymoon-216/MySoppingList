package kr.ohmymoon.mysoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListApp(){
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var itemName by remember {
        mutableStateOf("")
    }
    var itemQuantity by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems){

            }
        }
    }

    if(showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Add Shopping Item")
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        label = { Text(text = "Name") }
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = { itemQuantity = it },
                        label = { Text(text = "Quantity") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if(itemName.isNotBlank()) {
                            val newItem = ShoppingItem(
                                id = sItems.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toInt()
                            )
                            sItems = sItems + newItem
                            showDialog = false
                            itemName = ""
                            itemQuantity = ""
                        }
                    }
                ) {
                    Text(text = "Add")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        itemName = ""
                        itemQuantity = ""
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

data class ShoppingItem(
    val id:Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
)

@Composable
fun ShoppingListItem(
    item: shoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color(color = 0xFF018786)),
                shape = RoundedCornerShape(20)
            )
    ) {
        Text(text = item.name, modifier = Modifier.padding(8.dp))
    }
}
