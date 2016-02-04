# UPM - Day 4 - 04/02/2016 - How to protect your research

#Day 4 - Part 1

###Speaker: Ana Goicolea
GOICOLEA RUIGOMEZ, ANA<BR>
ana.goicolea@upm.es<BR>
913366192<BR>
913365974<BR>
https://es.linkedin.com/in/ana-goicolea-ruigómez-83b7742b/en<BR>

OTRIUPM<BR>

#Intellectual Property Rights (IPRs)

#Copyright

* Literary and artistic works (books, music, theatre, paintings …)
* Computer programs (Protect only the code source and not the idea of the software)
* Web pages and data bases

#Industrial Property

* Patents
* Registred Marks

#One product = multiple IPRs

* 1500 a 2000 patents (Data-processing methods, semiconductor circuits, chemical compounds, etc)
* Registered design (Shape of phone)
* Registered trade marks (Brand name, start-up and ring tone (i.e Nokia Tune)
* Copyright (Sofware, ringtones)

#What is a patent?

Is a contract between the State and the Inventor/Owner. <BR>
Exclusivity in a country for 20 years.<BR>

The inventor pay taxes to the state to keep the patents.<BR>

#Exclusions: What cannot be patented?
* presentations of information
* medical or diagnostic treatment
* mathematical methods, discoveries, scientific theories
* plants or animal varieties;
* schemas, rules and methods for performing mental acts playing games or doing business and programs for computers;

#Requirements: What can be patented?

Patents shall be granted for any invention, in any field of techonology

#What is the "state of the art"?

Everything (in the world) made avaible to the public by means of ... <BR>

* written description
* oral description
* by use
* or disclosed in any other way

... before the filing date of the application. <BR>

Keep your invention confidential until you have filed your application!

#Patents Databases

###Espacenet (Free)
* over 90 million patent documents, easily searchable
* All patents in the world in this database

http://worldwide.espacenet.com/<BR>


###Google Patent
* Only US and EU patents;

https://patents.google.com/<BR>

There is a tool to translate patents.<BR>

###Spanish Patent Office
* Application filed
* Search Report
* Publication (Invetion becomes public)
It takes 18 months.<BR>

###Examination Process
* Opposition period expires
* Prelim.exam
* Grant

The whole process is about Aprox. 2-3 years.<BR>

#International patent strategy
PCT Loop is to "buy" time. It is the way big companies use to patent in a lot of countries.<BR>

###PCT Contracting States (148 countries).
* USA, EU, Brazil, etc.

###Real Patents created in UPM
* Alise Devices ( http://www.alise-devices.com )

# Artículo 20 de la Ley de Patentes
# Art. 4 de la Normativa de PI de la UPM

# Trabajos de Fin de Master 
En el caso de un trabajo de fin de master el autor es el alumno y el titular del trabajo.<BR>

# Derechos económicos de los inventores
* Los inventores y/o autores percibirán el 50% de los ingresos obtenidos por el contrato de licencia. En el caso de que el resultado en explotación fuera derivado de trabajos docentes, los alumnos participantes ... percibirán, al menos, el 70% de este 50%.
El porcentaje a percibir por los autores o inventores se repartirá entre ellos según hayan especificiado en documento escrito ....... De mutuo acuerdo entre todos ellos, podrán destinar dichos recursos a actividades de fomento de la investigación en el grupo ...... a los que pertenezcan.
* El 50% restante se destinará a los usos que decida la UPM.

Art. 13 de la Normativa de PI de la UPM.<BR>

# What must a researcher do if he/she has a patentable invention?
* Find out if the technology already exists
* Avoid disclosing information before patent ap[lication
* Seek for advice to a patent expert (Patent attorney or OTRI in case of university research)

#El coste aproximado de registrar una patente:
* El coste para la UPM es aprox. 2000 a 3000 euros. (Ellos hasta el momento tienen descuentos)
* 30.000 euros - USA and Europe

UPM is the third in Spain with number of patents.<BR>

# What is copyright

Copyright is a form of protection for original works of authorship fixed

#What does copyright protects?

70 years after the death of the author.


# Day 4 - Part 2

###Speaker: Victor Rodriguez Doncel
Victor Rodriguez-Doncel (1978) is a postdoc researcher at the Ontology Engineering Group, in the Universidad Politécnica de Madrid.<BR>
http://delicias.dia.fi.upm.es/~vrodriguez/<BR>
https://es.linkedin.com/in/victor-rodriguez-doncel-554b5213/en<BR>
https://twitter.com/vroddon<BR>


#General Idea
Software Licensing<BR>

Software is in the same category as a literary or artistic work... <BR>

Berne Convention for the Protection of Literary and Artistic Works <BR>


* Public domain
* Free software
- Very Week restrictions 
- Week restrictions (Apache, Original BSB, MIT, W3C)
- Strong (GPL, X11, Modified BSD)


# Copyleft 
* Copyleft is a general method for making a program free software requiring all modified and extended versions of the program to be free software as well.
* Copyleft takes away the freedom to turn the software into a propritary (i.e. non-free) software.

If a program 

GNU GPL 
* General Public License
* 

FSF (Free Software Foundation)
OSF (Open Source Foundation)

#End User License Agreement?
* Not to confuse 

#Marca de agua
Dejar algo en tu software, es un señal invisible que se deja en canciones, imagens, softwares.

http://www.softonic.com/windows/marca-de-agua<BR>
http://conunpocodeazucar.com/como-poner-una-marca-de-agua-a-varias-fotos-de-golpe/<BR>

#Apache Rat
Apache Rat is a release audit tool, focused on licenses.<BR>
http://creadur.apache.org/rat/<BR>

# How to install and Run Apache Rat

###SVN Checkout the code in a folder
```
cd /Users/caiomsouza/svn 
svn co http://svn.apache.org/repos/asf/creadur/rat/trunk/
```

###Build Rat
```
cd /Users/caiomsouza/svn/apache.rat/trunk
mvn clean install
```

###Run Apache Rat
```
cd /Users/caiomsouza/svn/apache.rat/trunk/apache-rat/target
java -jar apache-rat-0.12-SNAPSHOT.jar 
```
