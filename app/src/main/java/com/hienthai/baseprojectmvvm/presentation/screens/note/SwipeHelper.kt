package com.hienthai.baseprojectmvvm.presentation.screens.note

import androidx.recyclerview.widget.ItemTouchHelper
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity

class SwipeHelper(onSwiped: (NoteEntity) -> Unit,): ItemTouchHelper(SwipeCallback(onSwiped))