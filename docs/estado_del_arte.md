# Estado del Arte: Modelos de Control de Acceso

El control de acceso ha evolucionado de estructuras rígidas y jerárquicas a sistemas dinámicos basados en el contexto. A continuación, se presentan los seis métodos fundamentales que definen el panorama actual de la ciberseguridad.

---

## 1. Control de Acceso Basado en Roles (RBAC)

El modelo RBAC asigna permisos a funciones de trabajo (roles) en lugar de a individuos. Es la piedra angular de la seguridad empresarial debido a su capacidad para reflejar la estructura organizacional de una compañía.

- **Uso común:** Sistemas ERP, gestión de empleados y aplicaciones SaaS estándar.

---

## 2. Control de Acceso Basado en Atributos (ABAC)

ABAC utiliza políticas que combinan atributos del usuario, del recurso y del entorno. A diferencia de RBAC, permite decisiones en tiempo real (ej. "permitir acceso solo si el usuario está en la oficina y es horario laboral").

- **Uso común:** Sistemas de alta seguridad y entornos de nube distribuida.

---

## 3. Control de Acceso Basado en Políticas (PBAC)

El PBAC es un enfoque estratégico donde las reglas de negocio se abstraen en políticas lógicas. Se centra en el "qué" se permite bajo qué condiciones legales o de cumplimiento, facilitando la auditoría de gobernanza.

- **Uso común:** Cumplimiento de normativas como GDPR o HIPAA.

---

## 4. Control de Acceso Discrecional (DAC)

En el modelo DAC, el propietario de un objeto (un archivo o directorio) tiene la potestad de conceder o revocar el acceso a otros usuarios a su propia discreción. Es el modelo más flexible pero el menos seguro en entornos críticos.

- **Uso común:** Sistemas de archivos tradicionales (Windows NT, Unix).

---

## 5. Control de Acceso Obligatorio (MAC)

El MAC es el modelo más restrictivo. El acceso es determinado por una autoridad central basada en niveles de confidencialidad y etiquetas de seguridad. El usuario no puede cambiar los permisos de los recursos que posee.

- **Uso común:** Sistemas gubernamentales, militares y núcleos de sistemas operativos de alta seguridad (SELinux).

---

## 6. Control de Acceso Basado en la Identidad (IBAC)

El IBAC es la forma más simple de control, donde el acceso se otorga basándose directamente en la identidad única del individuo, usualmente mediante listas de control de acceso (ACL). Cada usuario tiene una lista específica de recursos a los que puede acceder.

- **Uso común:** Listas de invitados en aplicaciones pequeñas o permisos de carpetas compartidas individuales.

---

## Tabla Comparativa

| Modelo | Mecanismo Principal      | Nivel de Control | Escalabilidad |
|--------|--------------------------|------------------|---------------|
| RBAC   | Roles jerárquicos        | Medio            | Alta          |
| ABAC   | Atributos y contexto     | Muy Fino         | Alta          |
| PBAC   | Reglas de gobernanza     | Alto             | Media         |
| DAC    | Identidad del dueño      | Bajo             | Baja          |
| MAC    | Etiquetas de seguridad   | Muy Alto         | Baja          |
| IBAC   | Identidad del usuario    | Fino             | Muy Baja      |

---

## Conclusión: RBAC como modelo elegido

Tras analizar los seis modelos de control de acceso, se seleccionó **RBAC (Control de Acceso Basado en Roles)** como el enfoque más adecuado para este proyecto.

La decisión se fundamenta en los siguientes criterios:

1. **El dominio tiene roles claramente definidos.** El sistema cuenta con exactamente tres actores — `PLAYER`, `AUDITOR` y `ADMIN` — cada uno con un conjunto de acciones permitidas distinto y no superpuesto. Esto se mapea de forma natural a RBAC.

2. **Los permisos son estáticos y predecibles.** Ninguna decisión de acceso depende de contexto en tiempo de ejecución, atributos ambientales ni políticas de cumplimiento. El mismo rol siempre otorga los mismos permisos, lo que hace que RBAC sea suficiente y correcto.

3. **Simplicidad sin sacrificar seguridad.** RBAC ofrece una separación limpia de responsabilidades, es ampliamente comprendido, fácil de auditar y se integra de forma nativa con Spring Security mediante `@PreAuthorize` y `GrantedAuthority`, siendo la elección estándar de la industria para este tipo de aplicaciones.

4. **Escalabilidad.** Si el sistema creciera (por ejemplo, añadiendo un rol `SPECTATOR` o separando permisos de auditoría), RBAC puede extenderse con mínimo esfuerzo en comparación con reconstruir una estructura DAC o IBAC.

En resumen, RBAC no se elige por defecto o conveniencia — es el modelo técnicamente correcto para un sistema donde la identidad se mapea directamente a un rol organizacional bien definido con permisos fijos.

---

## Referencias Bibliográficas

- Ferraiolo, D. F., Kuhn, D. R., & Chandramouli, R. (2003). *Role-based access control*. Artech House.
- Hu, V. C., Kuhn, D. R., & Ferraiolo, D. F. (2015). Attribute-based access control. *IEEE Computer, 48*(2), 85–88. https://doi.org/10.1109/MC.2015.33
- National Institute of Standards and Technology (NIST). (2014). *Guide to Attribute Based Access Control (ABAC) Definition and Considerations* (Special Publication 800-162). U.S. Department of Commerce. https://doi.org/10.6028/NIST.SP.800-162
- Priebe, T., Musmeci, G., & Pernul, G. (2007). A policy-based access control framework for service-oriented architectures. *Proceedings of the 22nd International Information Security Conference (IFIP SEC 2007)*.
- Sandhu, R. S., & Samarati, P. (1994). Access control: Principle and practice. *IEEE Communications Magazine, 32*(9), 40–48. https://doi.org/10.1109/35.312842
- Smith, C. L. (2002). Understanding discretionary access control and mandatory access control. *Journal of Information Warfare, 1*(2), 29–37.
