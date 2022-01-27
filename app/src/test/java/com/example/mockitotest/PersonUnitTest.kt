package com.example.mockitotest

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PersonUnitTest {

    private lateinit var SUT : Persona

    @Mock
    private lateinit var mockedPerson : Persona

    @Before
    fun setUp(){
        SUT = Persona("Carlos")
    }

    @Test
    fun nombre_Carlos() {
        Assert.assertEquals(SUT.nombre, "Carlos")
    }

    @Test
    fun miNombreEnFrase_Carlos() {
        Assert.assertEquals(SUT.miNombreEnFrase(), "Me llamo Carlos")
    }

    @Test
    fun nombre_MockedPaco() {
        Mockito.`when`(mockedPerson.nombre).thenReturn("Paco")
        Assert.assertEquals(mockedPerson.nombre, "Paco")
    }

    @Test
    fun miNombreEnFrase_MockedPaco() {
        Mockito.`when`(mockedPerson.miNombreEnFrase()).thenReturn("Mi nombre es Paco")
        Assert.assertEquals(mockedPerson.miNombreEnFrase(), "Mi nombre es Paco")
    }

    @Test
    fun tengoHermano_No() {
        Assert.assertFalse(SUT.tengoHermano())
    }


    @Test
    fun tengoHermano_Si_Test_Error() {
        Mockito.`when`(mockedPerson.hermano).thenReturn(SUT)

        println(mockedPerson.hermano?.nombre)
        println(mockedPerson.decirNombreHermano())

        //Assert.assertTrue(mockedPerson.tengoHermano())
        //Assert.assertEquals(mockedPerson.decirNombreHermano(), SUT.nombre)
    }

    @Test
    fun tengoHermano_Si() {
        Mockito.`when`(mockedPerson.tengoHermano()).thenReturn(true)
        Mockito.`when`(mockedPerson.decirNombreHermano()).thenReturn("Carlos")

        Assert.assertTrue(mockedPerson.tengoHermano())
        Assert.assertEquals(mockedPerson.decirNombreHermano(), SUT.nombre)
    }

    @Test
    fun tengoHermano_spy() {
        val persona = Persona("Alfredo")
        val personaSpied = Mockito.spy(persona)
        personaSpied.hermano = SUT
        Mockito.`when`(personaSpied.tengoHermano()).thenReturn(false)

        println(personaSpied.hermano?.nombre)
        println(personaSpied.tengoHermano())
        println(personaSpied.decirNombreHermano())

        val mocked = Mockito.mock(Persona::class.java)
        mocked.hermano = SUT
        Mockito.`when`(mocked.tengoHermano()).thenReturn(false)

        println(mocked.hermano?.nombre)
        println(mocked.tengoHermano())
        println(mocked.decirNombreHermano())

    }
}