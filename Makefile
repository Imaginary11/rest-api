ifndef RELEASE_DIR
	RELEASE_DIR = $(PWD)/build
endif
export RELEASE_DIR
compile_dir=${PWD}
APP_NAME = rest-api
CONFIG_DIR = usr/local/${APP_NAME}
version = $(shell svn info |grep "Last Changed Rev:" | awk -F ': ' '{print $$2}')
pom_version = $(shell svn info pom.xml |grep "Last Changed Rev:" | awk -F ': ' '{print $$2}')
build_dir:
	rm -rf ${RELEASE_DIR}
	mkdir -p $(RELEASE_DIR)/$(CONFIG_DIR)/conf
	mkdir -p $(RELEASE_DIR)/$(CONFIG_DIR)/lib
	mkdir -p $(RELEASE_DIR)/$(CONFIG_DIR)/bin
	mkdir -p $(RELEASE_DIR)/$(CONFIG_DIR)/log
install: build_dir
	rm -rf ${APP_NAME}-1.0-r*.rls
	mvn clean package -Dmaven.test.skip=true
	cp -a target/${APP_NAME}-1.0.jar.original $(RELEASE_DIR)/$(CONFIG_DIR)/lib/${APP_NAME}-1.0.jar.original
	unzip target/${APP_NAME}-1.0.zip -d target/
	cp -rf target/${APP_NAME}-1.0/lib/* $(RELEASE_DIR)/$(CONFIG_DIR)/lib
	cp -a  target/${APP_NAME}-1.0/config/* $(RELEASE_DIR)/$(CONFIG_DIR)/conf
	cp -a shell/${APP_NAME} $(RELEASE_DIR)/$(CONFIG_DIR)/bin
	cp -a shell/health_check $(RELEASE_DIR)/$(CONFIG_DIR)/bin
	cp -a shell/install $(RELEASE_DIR)
	cp -a shell/initx $(RELEASE_DIR)/$(CONFIG_DIR)/bin
	cp -a shell/rest_service.cfg $(RELEASE_DIR)/$(CONFIG_DIR)/conf
	echo "$(version)" > $(RELEASE_DIR)/$(CONFIG_DIR)/version
	chmod +x $(RELEASE_DIR)/$(CONFIG_DIR)/bin/${APP_NAME}
	chmod +x $(RELEASE_DIR)/$(CONFIG_DIR)/bin/health_check
	chmod +x $(RELEASE_DIR)/$(CONFIG_DIR)/bin/initx
	chmod +x  $(RELEASE_DIR)/install
	cd ${RELEASE_DIR}  && tar zcf ../${APP_NAME}-1.0-r${version}.rls ./*
	ls -lh ${APP_NAME}-1.0-r${version}.rls | awk -F ' ' '{print $$5}'> $(RELEASE_DIR)/$(CONFIG_DIR)/package.info
	echo "r${version}" >> $(RELEASE_DIR)/$(CONFIG_DIR)/package.info
	rm -rf ${APP_NAME}-1.0-r${version}.rls
	cd ${RELEASE_DIR}  && tar zcf ../${APP_NAME}-1.0-r${version}.rls ./*
ifneq (${version},${pom_version})
	rm -rf $(RELEASE_DIR)/$(CONFIG_DIR)/lib/*
	cp -a target/${APP_NAME}-1.0.jar.original $(RELEASE_DIR)/$(CONFIG_DIR)/lib/${APP_NAME}-1.0.jar.original
	cd ${RELEASE_DIR}  && tar zcf ../${APP_NAME}-1.0-r${version}-fup\(r${pom_version}\).rls ./* 
	ls -lh ${APP_NAME}-1.0-r${version}-fup\(r${pom_version}\).rls | awk -F ' ' '{print $$5}'> $(RELEASE_DIR)/$(CONFIG_DIR)/package.info
	echo "r${version}" >> $(RELEASE_DIR)/$(CONFIG_DIR)/package.info
	rm -rf ${APP_NAME}-1.0-r${version}-fup\(r${pom_version}\).rls
	cd ${RELEASE_DIR}  && tar zcf ../${APP_NAME}-1.0-r${version}-fup\(r${pom_version}\).rls ./*
endif
	rm -rf target
	rm -rf ${RELEASE_DIR}
clean:
	rm -rf target
	rm -rf $(RELEASE_DIR)

