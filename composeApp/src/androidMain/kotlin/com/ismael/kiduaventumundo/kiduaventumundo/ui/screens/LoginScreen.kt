
package com.ismael.kiduaventumundo.kiduaventumundo.ui.screens

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

import androidx.lifecycle.viewmodel.compose.viewModel

import com.ismael.kiduaventumundo.kiduaventumundo.R
import com.ismael.kiduaventumundo.kiduaventumundo.ui.viewmodel.LoginViewModel


@Composable
fun LoginScreen(
    onGoRegister: () -> Unit,
    onLoginSuccess: () -> Unit
) {

    val context = LocalContext.current
    val viewModel: LoginViewModel = viewModel()

    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()


    // Navegación cuando el login sea exitoso
    LaunchedEffect(viewModel.loginSuccess) {
        if (viewModel.loginSuccess) {
            onLoginSuccess()
        }
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Fondo
        AsyncImage(
            model = R.drawable.fondo_registro,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        // Búho animado
        AsyncImage(
            model = R.drawable.hello,
            imageLoader = imageLoader,
            contentDescription = "Buho saludando",
            modifier = Modifier
                .size(260.dp)
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
        )


        // Card Login
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(Alignment.Center)
                .offset(y = 70.dp),

            shape = RoundedCornerShape(32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(26.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Iniciar Sesión",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))


                // Campo nickname
                OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    label = { Text("Nickname") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )


                Spacer(modifier = Modifier.height(16.dp))


                // Campo contraseña
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,

                    visualTransformation =
                        if (passwordVisible) VisualTransformation.None
                        else PasswordVisualTransformation(),

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),

                    trailingIcon = {

                        val icon =
                            if (passwordVisible)
                                Icons.Filled.VisibilityOff
                            else
                                Icons.Filled.Visibility

                        IconButton(
                            onClick = {
                                passwordVisible = !passwordVisible
                            }
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = "Mostrar contraseña"
                            )
                        }
                    }
                )


                Spacer(modifier = Modifier.height(10.dp))


                // Mostrar error
                viewModel.errorMessage?.let {

                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }


                Spacer(modifier = Modifier.height(16.dp))


                // Botón login
                Button(
                    onClick = {
                        viewModel.login(nickname, password)
                    },

                    enabled = !viewModel.isLoading,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),

                    shape = RoundedCornerShape(20.dp)
                ) {

                    if (viewModel.isLoading) {

                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(22.dp)
                        )

                    } else {

                        Text("Entrar")

                    }
                }


                Spacer(modifier = Modifier.height(12.dp))


                // Ir a registro
                TextButton(
                    onClick = onGoRegister
                ) {

                    Text("¿No tienes cuenta? Regístrate")

                }

            }

        }

    }

}

