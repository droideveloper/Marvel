package io.fs.marvel.model.event

import io.fs.marvel.model.data.Character
import org.fs.architecture.mvi.common.Event

class SelectCharacterEvent(val character: Character): Event