@file:OptIn(ExperimentalMaterial3Api::class)

package com.pamn.museo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.pamn.museo.model.AppScreens

@Composable
fun BottomNavBar(navController: NavController){
    val items = listOf(
        BottomNavigationItem(
            title="Home",
            selectedIcon= Icons.Filled.Home,
            unselectedIcon= Icons.Outlined.Home,
            route= AppScreens.Home.route,
            hasNews = false
        ),
        BottomNavigationItem(
            title="Login",
            selectedIcon= Icons.Filled.Person,
            unselectedIcon= Icons.Outlined.Person,
            route= AppScreens.Login.route,
            hasNews = false
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold (
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(badge = {
                                if(item.hasNews){
                                    Badge()
                                }
                            }) {
                                Icon(
                                    imageVector = if(index == selectedItemIndex){
                                        item.selectedIcon
                                    }else item.unselectedIcon
                                    , contentDescription = item.title)
                            }
                        })
                }
            }
        }
    ){}
}
