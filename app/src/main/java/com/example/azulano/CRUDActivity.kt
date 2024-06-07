package com.example.azulano

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.azulano.databinding.ActivityCrudactivityBinding
import com.example.azulano.model.Animal
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CRUDActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrudactivityBinding

    private val database = FirebaseDatabase.getInstance()
    private val databaseReference = database.getReference("animals")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrudactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            val animalName = binding.editTextAnimalName.text.toString()
            if (animalName.isNotEmpty()) {
                addAnimal(animalName)
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonUpdate.setOnClickListener {
            val animalName = binding.editTextAnimalName.text.toString()
            if (animalName.isNotEmpty()) {
                updateAnimal(animalName)
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonDelete.setOnClickListener {
            val animalName = binding.editTextAnimalName.text.toString()
            if (animalName.isNotEmpty()) {
                deleteAnimal(animalName)
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonRead.setOnClickListener {
            readAnimals()
        }
    }

    private fun addAnimal(name: String) {
        val id = databaseReference.push().key
        if (id != null) {
            val animal = Animal(id, name)
            databaseReference.child(id).setValue(animal).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Animal added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to add animal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateAnimal(name: String) {
        val query = databaseReference.orderByChild("name").equalTo(name)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (animalSnapshot in snapshot.children) {
                        val animal = animalSnapshot.getValue(Animal::class.java)
                        if (animal != null) {
                            animalSnapshot.ref.child("name").setValue("$name Updated")
                        }
                    }
                    Toast.makeText(this@CRUDActivity, "Animal updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@CRUDActivity, "Animal not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CRUDActivity, "Failed to update animal", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteAnimal(name: String) {
        val query = databaseReference.orderByChild("name").equalTo(name)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (animalSnapshot in snapshot.children) {
                        animalSnapshot.ref.removeValue()
                    }
                    Toast.makeText(this@CRUDActivity, "Animal deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@CRUDActivity, "Animal not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CRUDActivity, "Failed to delete animal", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun readAnimals() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val animals = StringBuilder()
                    for (animalSnapshot in snapshot.children) {
                        val animal = animalSnapshot.getValue(Animal::class.java)
                        if (animal != null) {
                            animals.append("ID: ${animal.id}, Name: ${animal.name}\n")
                        }
                    }
                    binding.textViewAnimals.text = animals.toString()
                } else {
                    Toast.makeText(this@CRUDActivity, "No animals found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CRUDActivity, "Failed to read animals", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
