job('ejeplo-job-DSL') {
			description('DSLde ejemplo para el curso de Jenkins')
		  scm {
				git('https://github.com/edolilloYahoo/-jenkins.job.parametrizado.git', 'main') { node -> // is hudson.plugins.git.GitSCM
					node / gitConfigName('edolillo')
					node / gitConfigEmail('edolillo@ymail.com')
				}
			}
		  parameters {
				stringParam('nombre', defaultValue = 'Eduardo', description = 'Parametros de cadena para el Job DSL')
				choiceParam('Planeta', ['Mercurio (default)', 'Venus', 'tierra', 'Marte', 'Jupiter','Sarturo', 'Urano', 'Pluton'])
				booleanParam('Agente', false)
			}
		  triggers {
				cron('H/7 * * * *')
			  	githubPush()
			}
		  steps {
				shell("bash jobscript.sh")
		   }
		  publishers {
				mailer('edolillo@ymail.com', true, true)
				slackNotifier {
				  notifyAborted(true)
				  notifyEveryFailure(true)
				  notifyNotBuilt(false)
				  notifyUnstable(false)
				  notifyBackToNormal(true)
				  notifySuccess(false)
				  notifyRepeatedFailure(false)
				  startNotification(false)
				  includeTestSummary(false)
				  includeCustomMessage(false)
				  customMessage(null)
				  sendAs(null)
				  commitInfoChoice('NONE')
				  teamDomain(null)
				  authToken(null)
				}
		  }
		}
