package com.ismael.kiduaventumundo.kiduaventumundo.ui.navigation

// ============================
// Compose
// ============================
import androidx.compose.runtime.Composable

// ============================
// Navigation
// ============================
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// ============================
// Screens
// ============================
import com.ismael.kiduaventumundo.kiduaventumundo.ui.screens.*

// ============================
// ViewModels
// ============================
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ismael.kiduaventumundo.kiduaventumundo.ui.viewmodel.ProfileViewModel

// ============================
// Session
// ============================
import com.ismael.kiduaventumundo.kiduaventumundo.datasource.repository.SessionRepository


@Composable
fun AndroidApp() {

    val navController = rememberNavController()
    val sessionRepository = SessionRepository()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        // =====================================================
        // SPLASH SCREEN
        // =====================================================
        composable(Routes.SPLASH) {

            SplashScreen(
                hasSession = sessionRepository.hasSession(),

                onGoLogin = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                },

                onGoMenu = {
                    navController.navigate(Routes.MENU) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }


        // =====================================================
        // LOGIN SCREEN
        // =====================================================
        composable(Routes.LOGIN) {

            LoginScreen(

                onLoginSuccess = {
                    navController.navigate(Routes.MENU) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },

                onGoRegister = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }


        // =====================================================
        // REGISTER SCREEN
        // =====================================================
        composable(Routes.REGISTER) {

            RegisterScreen(

                onRegisterSuccess = {
                    navController.navigate(Routes.MENU) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                }
            )
        }


        // =====================================================
        // MENU (HOME)
        // =====================================================
        composable(Routes.MENU) {

            val profileViewModel: ProfileViewModel = viewModel()

            MenuScreen(
                nickname = "Usuario",
                profileViewModel = profileViewModel,

                onGoEnglish = {
                    // navController.navigate(Routes.ENGLISH)
                },

                onGoProfile = {
                    // navController.navigate(Routes.PROFILE)
                }
            )
        }


        // =====================================================
        // PROFILE
        // =====================================================
        /*
        composable(Routes.PROFILE) {
            ProfileScreen(...)
        }
        */


        // =====================================================
        // ENGLISH MENU
        // =====================================================
        /*
        composable(Routes.ENGLISH) {
            EnglishMenuScreen(...)
        }
        */


        // =====================================================
        // ENGLISH ACTIVITIES
        // =====================================================
        /*
        composable(Routes.ENGLISH_ACTIVITIES) {
            EnglishActivitiesScreen(...)
        }
        */


        // =====================================================
        // ENGLISH LEVELS
        // =====================================================
        /*
        composable(Routes.ENGLISH_LEVEL_1) { ... }
        composable(Routes.ENGLISH_LEVEL_2) { ... }
        composable(Routes.ENGLISH_LEVEL_3) { ... }
        composable(Routes.ENGLISH_LEVEL_4) { ... }
        composable(Routes.ENGLISH_LEVEL_5) { ... }
        composable(Routes.ENGLISH_LEVEL_6) { ... }
        composable(Routes.ENGLISH_LEVEL_7) { ... }
        composable(Routes.ENGLISH_LEVEL_8) { ... }
        */
    }
}