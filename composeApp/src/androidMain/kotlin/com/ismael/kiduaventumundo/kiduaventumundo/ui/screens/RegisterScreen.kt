package com.ismael.kiduaventumundo.kiduaventumundo.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.ismael.kiduaventumundo.kiduaventumundo.R
import com.ismael.kiduaventumundo.kiduaventumundo.ui.components.*
import com.ismael.kiduaventumundo.kiduaventumundo.ui.screens.steps.*
import com.ismael.kiduaventumundo.kiduaventumundo.ui.viewmodel.RegisterViewModel
import com.ismael.kiduaventumundo.kiduaventumundo.ui.model.Avatar

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit
) {

    val viewModel: RegisterViewModel = viewModel()
    var step by rememberSaveable { mutableStateOf(1) }

    val uiState = viewModel.uiState

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            onRegisterSuccess()
        }
    }

    val avatars = listOf(
        Avatar(1, R.drawable.avatar_gato),
        Avatar(2, R.drawable.avatar_perro),
        Avatar(3, R.drawable.avatar_leon),
        Avatar(4, R.drawable.avatar_oso)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.fondo_registro),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        CloudLayer()
        FlowerLayer()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            ProgressBar(step)

            Spacer(modifier = Modifier.weight(1f))

            RegisterCard {

                AnimatedContent(
                    targetState = step,
                    transitionSpec = {

                        if (targetState > initialState) {

                            slideInHorizontally(
                                animationSpec = tween(350),
                                initialOffsetX = { it }
                            ) + fadeIn() togetherWith
                                    slideOutHorizontally(
                                        animationSpec = tween(350),
                                        targetOffsetX = { -it }
                                    ) + fadeOut()

                        } else {

                            slideInHorizontally(
                                animationSpec = tween(350),
                                initialOffsetX = { -it }
                            ) + fadeIn() togetherWith
                                    slideOutHorizontally(
                                        animationSpec = tween(350),
                                        targetOffsetX = { it }
                                    ) + fadeOut()
                        }
                    },
                    label = ""
                ) { currentStep ->

                    when (currentStep) {

                        1 -> NameStep(
                            onNext = {
                                viewModel.updateName(it)
                                step = 2
                            }
                        )

                        2 -> AgeStep(
                            onNext = {
                                viewModel.updateAge(it)
                                step = 3
                            },
                            onBack = { step = 1 }
                        )

                        3 -> AvatarStep(
                            avatars = avatars,
                            onNext = {
                                viewModel.updateAvatar("avatar_$it")
                                step = 4
                            },
                            onBack = { step = 2 }
                        )

                        4 -> AccountStep(
                            onNext = { nick, pass ->
                                viewModel.updateNickname(nick)
                                viewModel.updatePassword(pass)
                                step = 5
                            },
                            onBack = { step = 3 }
                        )

                        5 -> SecurityStep(
                            onFinish = { q, a ->
                                viewModel.updateSecurityQuestion(q)
                                viewModel.updateSecurityAnswer(a)
                                viewModel.registerUser()
                            },
                            onBack = { step = 4 }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}