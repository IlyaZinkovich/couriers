describe('Controller: DepartmentController', function() {
	// Instantiate a new version of my module before each test
	beforeEach(module('companyApp'));
	var ctrl;
	// Before each unit test, instantiate a new instance
	// of the controller
	beforeEach(inject(function($controller) {
		ctrl = $controller('DepartmentController');
	}));
	it('should have departments available on load', function() {
		expect(ctrl.departments).toEqual([
		{name : 'Cobalt Maintenance'}, {name : 'TRI-CPL'}, {name : 'FS Maintenance'}
		]);
	});
	it('should have highlight items based on state', function() {
		var dept = {id: 1};
		ctrl.update(dept);
		expect(dept.name).toEqual("");
	});
});