//Declaraciones Iniciales 
int ledAlarma = 8;    //LedRojo
int buzzer = 9;       //Buzzer 
int ledAlerta1 = 12;  //LedVerde Intermitente
int ledAlerta2 = 13;   //Led Verde Advertencia
// Pin Analogo para el Sensor en 'A0'
int sensor = A0;
// Inicializar valor de sensor
int sensorValue = 0;

// Preparar Arduino
void setup() {
  // Inicializa OUTPUT
  pinMode(ledAlarma, OUTPUT);
  pinMode(buzzer, OUTPUT);
  pinMode(ledAlerta1, OUTPUT);
  pinMode(ledAlerta2, OUTPUT);
  // Inicializa COmunicacion serial a 9600 bit/s
  Serial.begin(9600);
}

// Comienza Loop
void loop() {
  //Lee sensor 
  sensorValue = analogRead(sensor);
  //Imprime en serial
  //Serial.print("Monoxido: ");
  Serial.println(sensorValue);
 // Serial.println(" ppm");
  //Serial.println(sensorValue, DEC);


  ///////////////////////////////////////////////////////////////////////////////
  // Si el sensor pasa IF enciende Alerta 1
  if (sensorValue < 100 ) {
    // Niveles Normales
    //Serial.println("AMBIENTE LIBRE DE MONOXIDO DE CARBONO");
  }
  ///////////////////////////////////////////////////////////////////////////////
  // Si el sensor pasa IF enciende Alerta 1
  if (sensorValue >= 100 && sensorValue < 300) {
    // Activa Alarma
   // Serial.println("SE DETECTA MONOXIDO DE CARBONO EN EL AMBIENTE");
    digitalWrite(ledAlerta1, HIGH); //Led Verde activo

  }
  else {
    // Activa led de Alerta
    digitalWrite(ledAlerta1, LOW); //Led Verde apagado
  }
  ///////////////////////////////////////////////////////////////////////////////
  // Si el sensor pasa IF enciende Alerta 2
  if (sensorValue >= 300 && sensorValue < 400) {
    // Activa Alarma
   // Serial.println("LOS NIVELES DE MONOXIDO DE CARBONO SON ANORMALES");
    digitalWrite(ledAlerta2, HIGH); //Led Amarillo activo

    //Activa BUZZER
     analogWrite(buzzer,100); //emite sonido bajo
     delay(500); //espera medio segundo
     digitalWrite(buzzer, LOW); //deja de emitir
     delay(500);//espera medio segundo
     //BUZZER
  }
  else {
    // Activa led de Alerta
    digitalWrite(ledAlerta2, LOW); //Led amarillo apagado
  }
  
  ///////////////////////////////////////////////////////////////////////////////
  // Si el sensor pasa IF enciende ALARMA
  if (sensorValue >= 400) {
    // Activa Alarma
   // Serial.println("EL NIVEL DE MONOXIDO DE CARBONO ES ALTAMENTE TOXICO");
   // Serial.println("TOME LAS PRECAUSIONES DEBIDAS INMEDIATAMENTE...");
    digitalWrite(ledAlarma, HIGH); //Led Rojo activo

    //Activa BUZZER
     analogWrite(buzzer,200); //emite sonido alto
     delay(1000); //espera medio segundo
     digitalWrite(buzzer, LOW); //deja de emitir
     delay(1000);//espera medio segundo
     //BUZZER
  }
  else {
    // Activa led de Alerta
    digitalWrite(ledAlarma, LOW); //Led rojo apagado
  }


  
  delay(1000);
  
}
