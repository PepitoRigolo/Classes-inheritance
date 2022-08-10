package fr.pepitorigolo.heritage

import kotlin.math.PI

fun main() {
    /**
     * Création d'une instance de SquareCabin qui vaut 6 residents et 50.0 de length
     * Création d'une instance de RoundHut qui vaut 3 residents et 10.0 de radius
     * Création d'une instance de RoundTower qui vaut 4 et 15.5 de radius
     */
    val squareCabin = SquareCabin(6, 50.0)
    val roundHut = RoundHut(3, 10.0)
    val roundTower = RoundTower(4, 15.5)


    /**
     * with(squareCabin) permet d'éviter les répétitions de println("${squareCabin.capactity}")
     * pour juste mettre println("${capacity}")
     */
    with(squareCabin){
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }

    with(roundHut){
        println("\nRound Hut\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }

    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }
}

/**
 * La classe Dwelling est une clase abstraite càd qu'elle est pas entiérement impleméntée
 * comme un croquis, elle intégère les idées et les plans de qqch mais pas assez d'information
 * pour la construire
 */
abstract class Dwelling(private var residents: Int) {
    abstract val buildingMaterial: String
    abstract val capacity: Int

    fun hasRoom(): Boolean {
        //si residents<capactity alors hasRoom vaut true sinon false
        return residents < capacity
    }

    //on doit implémentées dans l'une des sous classe cette méthode mtn
    abstract fun floorArea(): Double

}

/**
*   Pour dire que SquareCabin herite de la classe parent Dwelling on utilise ":"
*   Comme le nombre de résident va être variable on ajoute le paramètre residents à notre class de
 *   même pour la surface au sol
*/
class SquareCabin(residents: Int, val length: Double) : Dwelling(residents) {
    /**
     * Pour pouvoir utiliser les propriétées de la class abstraite Dwelling on doit les override
     * permet de faire comprendre qu'il sont sur le point d'être remplacée dans cette classe
     */
    override val buildingMaterial = "Wood"
    override val capacity = 6

    override fun floorArea(): Double {
        return length*length
    }
}

/**
 * Par défaut, dans Kotlin, les classes sont finales et ne peuvent pas être héritée de..
 * On doit marquées les classes avec le mot open pour pouvoir faire comprendre que Roundtower
 * hérite de RoundHut
 */
open class RoundHut(residents: Int, val radius: Double): Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4

    override fun floorArea(): Double {
        return PI * radius * radius
    }
}

/**
 * On ajoute un paramètre le nombre d'étape ce qui veut dire que l'on mutliplie la valeur par floor
 * pour avoir la capacity total
 */
class RoundTower(residents: Int, radius: Double, val floors: Int=2): RoundHut(residents, radius) {
    override val buildingMaterial = "Stone"
    override val capacity = 4*2


    override fun floorArea(): Double {
        return super.floorArea() * floors
    }
}