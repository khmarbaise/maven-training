You need to add the following to your settings.xml:

       <profile>
         <id>test-dev</id>
         <activation>
           <activeByDefault>false</activeByDefault>
          <file>
           <exists>${basedir}/README.md</exists>
          </file>
         </activation>
         <properties>
            <test.dev>yes</test.dev>
         </properties>

       </profile>

