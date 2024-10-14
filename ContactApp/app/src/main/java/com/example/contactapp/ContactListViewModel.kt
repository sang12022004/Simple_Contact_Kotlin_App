package com.example.contactapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.contracts.contract

class ContactListViewModel (
    private val contactRepository: ContactRepository = Graph.repository
) : ViewModel(){
    var state by mutableStateOf(ContactListState())
        private set

    var searchQuery by mutableStateOf("")

    init {
        getAllContacts()
    }
    private fun getAllContacts() {
        viewModelScope.launch {
            contactRepository.contacts.collectLatest {
                state = state.copy(
                    contacts = it,
                    filteredContacts = it
                )
                filterContacts()
            }
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.deleteContact(contact)
            filterContacts()
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery = query
        filterContacts()
    }

    private fun filterContacts() {
        if (searchQuery.isBlank()) {
            state = state.copy(filteredContacts = state.contacts)
        } else {
            viewModelScope.launch {
                contactRepository.searchContacts(searchQuery).collectLatest { filteredList ->
                    state = state.copy(filteredContacts = filteredList)
                }
            }
        }
    }

    fun getContactByID(id: Int) {
        viewModelScope.launch {
            contactRepository.getContactByID(id).collectLatest {
                state = state.copy(
                    contactFound = it
                )
            }
        }
    }
}

data class ContactListState(
    val  contacts: List<Contact> = emptyList(),
    val filteredContacts: List<Contact> = emptyList(),
    val contactFound: Contact = Contact(),
)