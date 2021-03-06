import chisel3._
import chisel3.Driver

/**
 * The blinking LED component.
 */

class Blinky(cycles : Int = 25000000) extends Module {
  val io = IO(new Bundle {
    val led = Output(UInt(1.W))
  })

  val cntReg = RegInit(0.U(32.W))
  val blkReg = RegInit(0.U(1.W))

  cntReg := cntReg + 1.U
  when(cntReg === (cycles - 1).U) {
    cntReg := 0.U
    blkReg := ~blkReg
  }
  io.led := blkReg
}

/**
 * An object extending App to generate the Verilog code.
 */
object Blinky extends App {
  chisel3.Driver.execute(Array[String](), () => new Blinky())
}
