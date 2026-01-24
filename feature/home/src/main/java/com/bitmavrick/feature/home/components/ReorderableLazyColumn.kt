package com.bitmavrick.feature.home.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun <T> ReorderableLazyColumn(
    items: List<T>,
    onReorder: (List<T>) -> Unit,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    itemContent: @Composable LazyItemScope.(item: T, index: Int, dragModifier: Modifier) -> Unit
) {
    var list by remember(items) { mutableStateOf(items) }
    var draggingItemIndex by remember { mutableIntStateOf(-1) }
    var dragOffset by remember { mutableFloatStateOf(0f) }
    
    val scope = rememberCoroutineScope()
    var scrollJob by remember { mutableStateOf<Job?>(null) }

    fun onDrag(offset: Float) {
        dragOffset += offset
        val draggingItem = state.layoutInfo.visibleItemsInfo.find { it.index == draggingItemIndex } ?: return
        val startOffset = draggingItem.offset.toFloat()
        val currentOffset = startOffset + dragOffset
        
        val targetItem = state.layoutInfo.visibleItemsInfo.find { item ->
            val itemStart = item.offset
            val itemEnd = item.offset + item.size
            currentOffset + draggingItem.size / 2 > itemStart && currentOffset + draggingItem.size / 2 < itemEnd
        }

        if (targetItem != null && targetItem.index != draggingItemIndex) {
            val newList = list.toMutableList()
            val item = newList.removeAt(draggingItemIndex)
            newList.add(targetItem.index, item)
            
            // Adjust drag offset to keep the item under the finger
            dragOffset += (draggingItem.offset - targetItem.offset)
            draggingItemIndex = targetItem.index
            list = newList
        }

        // Auto-scroll logic
        val viewportStart = 0f
        val viewportEnd = state.layoutInfo.viewportEndOffset.toFloat()
        val threshold = 100f

        if (currentOffset < viewportStart + threshold && scrollJob?.isActive != true) {
            scrollJob = scope.launch {
                state.animateScrollBy(-100f)
            }
        } else if (currentOffset + draggingItem.size > viewportEnd - threshold && scrollJob?.isActive != true) {
            scrollJob = scope.launch {
                state.animateScrollBy(100f)
            }
        }
    }

    LazyColumn(
        modifier = modifier,
        state = state
    ) {
        itemsIndexed(list) { index, item ->
            val isDragging = index == draggingItemIndex
            val elevation by animateDpAsState(if (isDragging) 8.dp else 0.dp)
            
            val dragModifier = Modifier.pointerInput(Unit) {
                detectDragGesturesAfterLongPress(
                    onDragStart = { draggingItemIndex = index },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        onDrag(dragAmount.y)
                    },
                    onDragEnd = {
                        onReorder(list)
                        draggingItemIndex = -1
                        dragOffset = 0f
                    },
                    onDragCancel = {
                        draggingItemIndex = -1
                        dragOffset = 0f
                    }
                )
            }

            Box(
                modifier = Modifier
                    .zIndex(if (isDragging) 1f else 0f)
                    .graphicsLayer {
                        translationY = if (isDragging) dragOffset else 0f
                        scaleX = if (isDragging) 1.05f else 1.0f
                        scaleY = if (isDragging) 1.05f else 1.0f
                    }
                    .animateItem()
            ) {
                itemContent(item, index, dragModifier)
            }
        }
    }
}

private suspend fun LazyListState.animateScrollBy(value: Float) {
    scrollBy(value)
}
