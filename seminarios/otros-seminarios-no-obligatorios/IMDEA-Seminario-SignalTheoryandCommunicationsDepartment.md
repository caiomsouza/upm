#Fernando Pérez-González
Signal Theory and Communications Department
Universidad de Vigo
Place: IMDEA / UPM (http://www.imdea.org/)

#Problems:

* Multimedia Forensics
* Steganography = Put data inside images. Steganalyzer = Analyze images to see if there is data inside images.
* Watermarking Detector
* Intrusion Detection
* Anti-spam filtering
* Cognitive radio
* Biometric identification / verification
* Reputation Systems (Sybil detector)

Soren Kierkegard pharses

Neyman-Pearson based
Multiple/binary
Rule/property based
Increasing the dimensionality

#Normal Images? Normal Data?
To detect Steganography you can see the histogram of an image and see if the normal distribution of the image is acceptable.
Accept anything (any image) that have a normal distribution of images.
Kullback-Leibler distance as a good strategy for the defender.

Packages in networks and pixels in an images are not very independents between them.

#Attacks to histogram-based detectors

* In many “anormal detection” instances, there is some statistical property that normal data satisfy.
* Image proc.: Generalized gaussian distribution in DCT domain.
* TCP networks: Exponential inter-arrival time in non-congested networks.
* Even the optimal asymptotic detector for i.i.d sources is based on the histogram!!!
* In all these cases, the acceptance region for the defender R0 is based on the histogram.

If you are a cat and you want to be accepted as a duck, what is the minimum modifications you need to do to be accepted as a duck.

In anti-spam filter the words is where you have to change to not be catch as a span.

* If we use a Euclidean distance, then the problem can be solved

Movers distance

Pevny-Friderch

Adversarial Classification Reverse Engineering [Lowd]

Hill-climbing: Hook-Jeeves algorithm

Score quantization
BNSA Against JANIS

#The final idea
In the image case (Steganography), you need to create an image capable to fool the detector, you need to optimize the distortion that will be so good that will fool the detector.
The detectors will usually give the result was an Yes or No answer, that makes hard to you to understand that is the detector using to detect, so you can query the detector a lot of time with different images with small modifications until you now the pattern that the detector use to detect.

More information in a image, more distortion you will have in a image and it will be easier to the detector to find it.

#Tools (Open Source and Free)bec:
http://www.openstego.com
