package me.matsumo.koto.core.ui.theme

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MotionScheme
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
object NavTransitions {
    private val motion = MotionScheme.standard()
    private val expressiveMotion = MotionScheme.expressive()

    object SlideFromRight {
        fun enter(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            fadeIn(
                animationSpec = expressiveMotion.defaultEffectsSpec(),
            ) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = expressiveMotion.defaultSpatialSpec(),
            )
        }

        fun exit(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            fadeOut(
                animationSpec = expressiveMotion.defaultEffectsSpec(),
            ) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = expressiveMotion.defaultSpatialSpec(),
            )
        }

        fun popEnter(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            fadeIn(
                animationSpec = expressiveMotion.defaultEffectsSpec(),
            ) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = expressiveMotion.defaultSpatialSpec(),
            )
        }

        fun popExit(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            fadeOut(
                animationSpec = expressiveMotion.defaultEffectsSpec(),
            ) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = expressiveMotion.defaultSpatialSpec(),
            )
        }
    }

    object SlideFadeFromRight {
        fun enter(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            fadeIn(
                animationSpec = motion.defaultEffectsSpec(),
            ) + slideInHorizontally(
                initialOffsetX = { fullWidth -> (fullWidth * 0.1).toInt() },
                animationSpec = motion.defaultSpatialSpec(),
            )
        }

        fun exit(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            fadeOut(
                animationSpec = motion.defaultEffectsSpec(),
            ) + slideOutHorizontally(
                targetOffsetX = { fullWidth -> -(fullWidth * 0.1).toInt() },
                animationSpec = motion.defaultSpatialSpec(),
            )
        }

        fun popEnter(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            fadeIn(
                animationSpec = motion.defaultEffectsSpec(),
            ) + slideInHorizontally(
                initialOffsetX = { fullWidth -> -(fullWidth * 0.1).toInt() },
                animationSpec = motion.defaultSpatialSpec(),
            )
        }

        fun popExit(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            fadeOut(
                animationSpec = motion.defaultEffectsSpec(),
            ) + slideOutHorizontally(
                targetOffsetX = { fullWidth -> (fullWidth * 0.1).toInt() },
                animationSpec = motion.defaultSpatialSpec(),
            )
        }
    }
}
