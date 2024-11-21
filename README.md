### Firmware Update Service

Ein einfacher REST-Service, der es ermöglicht:
- _Firmware-Updates zu prüfen:_ Geräte können anfragen, ob eine neue Firmware-Version verfügbar ist.
- _Neue Firmware-Versionen hinzuzufügen:_ Administratoren können neue Firmware-Informationen für bestimmte Hardware-IDs eintragen. 
- _Firmware-Versionen zu aktualisieren:_ Vorhandene Firmware-Einträge können aktualisiert werden.

##### Anforderungen
- Apache Maven: 3.8.1
- JDK: temurin-21
- Spring Boot   

Oder kompatible Versionen.

##### API-Dokumentation
Die vollständige API-Dokumentation befindet sich in der openapi.yaml unter src/main/resources/openapi.yaml
