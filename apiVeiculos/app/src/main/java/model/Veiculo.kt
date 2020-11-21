package model

class Veiculo(var placa: String, var modelo: String, var marca: String, var ano: Int, var motor: Double) {

    override fun toString(): String {
        return "placa: $placa, \nmodelo: $modelo, \nmarca: $marca, \nano: $ano, \nmotor: $motor"
    }
}


//class Veiculo {
//    var placa= ""
//    var modelo = ""
//    var marca = ""
//    var ano = 0
//    var motor = 0.0
//
//
//    constructor(placa:String, modelo:String, marca:String, ano:Int, motor:Double){
//        this.placa = placa
//    }
//    override fun toString(): String {
//        return this.placa
//    }
//}