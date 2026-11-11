# Taller para la Casa – La Oficina de Visas del Caos

**Asignatura:** Modelos de Programación  
**Docente:** Bocanegra  
**Integrantes:**
- Eddie Santiago Rondón Capera – 20251020108
- Luna Alejandra Sandoval Rodríguez – 20241020053

Proyecto Java que diagnostica y rescata los cuatro antipatrones del sistema
heredado del Consulado Caótico. La versión heredada vive en el paquete
`heredado` (tal como la dejó el equipo anterior) y la versión rescatada en
el paquete `rescate`, organizado por subpaquetes.

## Parte 1. Cacería de antipatrones

| Fragmento | Antipatrón | Síntoma que lo delata | Consecuencia en el consulado |
|---|---|---|---|
| 1. `OficinaDeTodo` | God Object | Una sola clase valida el pasaporte, cobra, guarda el expediente y envía el correo | Cuando Ramiro se enferma (o esa clase falla) el consulado entero se detiene; nadie más sabe hacer su trabajo |
| 2. `CalculadoraDeTarifas` | Copy-Paste Programming | La misma fórmula `50 * meses + 10` copiada en tres métodos | Al subir la tarifa base se corrigen dos copias y la tercera queda con el precio viejo: se cobra de más o de menos sin darse cuenta |
| 3. `EvaluadorMagico` | Magic Numbers | `tipo == 3`, `edad > 15` y `0.5` sin nombre ni explicación | Nadie sabe qué significa el 3; un tipo inválido como el 9 se rechaza en silencio y cualquier cambio de regla es una apuesta |
| 4. `SolicitudAcoplada` | Acoplamiento fuerte | La solicitud instancia sus servicios y los llama uno por uno a mano en `cancelar()` | El viernes olvidaron avisarle a la caja y a un viajero se le perdió el reembolso; agregar un aviso más obliga a tocar la clase |

## Parte 2. El rescate

| Antipatrón | Solución aplicada | Paquete |
|---|---|---|
| God Object | Responsabilidad única: `ValidadorPasaporte`, `Cajero`, `ArchivoExpedientes` y `EnviadorCorreos`, coordinados por `OficinaDeVisas` con inyección de dependencias | `rescate.oficina` |
| Copy-Paste Programming | Strategy: cada tipo de visa aporta su `EstrategiaTarifa` y la tarifa base vive una sola vez en `ParametrosTarifa` | `rescate.tarifas` |
| Magic Numbers | Enum `TipoVisa` con código y rango de edad con nombre, más `ResultadoEvaluacion` con mensajes claros | `rescate.evaluacion` |
| Acoplamiento fuerte | Observer: `SolicitudVisa` solo publica la cancelación y los observadores (`AvisoCorreo`, `AgendaCitas`, `CajaPagos`) reaccionan solos | `rescate.cancelacion` |

## Parte 3. La prueba de fuego

Los 5 casos de prueba se ejecutan en `src/app/Main.java` mostrando la
versión heredada y la rescatada una al lado de la otra. La salida completa
de la consola está en [`SALIDA_CONSOLA.txt`](SALIDA_CONSOLA.txt).

## Cómo compilar y ejecutar

```
javac -d bin $(find src -name "*.java")
java -cp bin app.Main
```

También se puede abrir en VS Code (carpeta `src` como source path) y
ejecutar `Main` desde la vista de Java.

## Reflexión: cómo no caer de nuevo en los antipatrones

La implementación evita recaer en los antipatrones porque cada decisión de
diseño deja el cambio en un solo lugar: ninguna clase acumula tareas (si
mañana aparece una nueva obligación de la oficina, se crea una clase nueva
y se inyecta en `OficinaDeVisas`), ninguna fórmula se repite (subir la
tarifa base es tocar `ParametrosTarifa` y nada más, como lo demostró el
caso 2), ningún valor anda sin nombre (un tipo de visa nuevo es una
constante más del enum con su rango de edad, y los códigos inválidos
explican su rechazo en lugar de fallar en silencio) y ningún servicio se
llama a mano desde otro (agregar un aviso de cancelación es crear un
observador y suscribirlo, sin modificar `SolicitudVisa`). En resumen: una
responsabilidad por clase, un único punto de cambio por concepto y
eventos en lugar de cadenas de llamadas.
